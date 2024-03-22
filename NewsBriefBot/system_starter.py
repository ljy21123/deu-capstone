# 데이터 종합 및 요약 시스템을 시작하는 코드입니다.
# 작성자: 양시현
# 수정 이력: 
# - 2024-03-20: 초기버전 생성
# - 2024-03-20: 콘솔 출력이 아닌 로그 파일로 기록하도록 수정, 
# 해야할 일
# - 카카오톡 알림 연결
# - 뉴스 중복 여부 확인
# - 도어 공지 중복 여부 확인
# - DB연결

import threading
import queue
import logging
from datetime import datetime, time, timedelta
from time import sleep

import naverNews
import newsBriefBot
import doorNotification


# 작업을 관리할 큐
global_task_queue = queue.Queue()
# 이벤트 생성
gueue_event = threading.Event()
# 로거
logger = None

def setup_logger(name, log_file, level=logging.INFO):
    formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s - %(message)s')
    handler = logging.FileHandler(log_file, encoding='utf-8')
    handler.setFormatter(formatter)

    logger = logging.getLogger(name)
    logger.setLevel(level)
    logger.addHandler(handler)

    # 콘솔에도 로그 출력
    console = logging.StreamHandler()
    console.setLevel(logging.INFO)
    formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s - %(message)s')
    console.setFormatter(formatter)
    logging.getLogger('').addHandler(console)


# newsbriefbot.py를 실행하는 함수
def run_news_briefbot():
    news_briefbot_thread = threading.Thread(target=newsBriefBot.run_brief, args=(global_task_queue, gueue_event))
    news_briefbot_thread.start()
    logger.info('뉴스 브리핑 봇 동작 시작')


# naverNews.py를 실행하는 함수
def run_naver_news_parsing():
    logger.info('네이버 뉴스 크롤링 수행시작')
    naver_news_thread = threading.Thread(target=naverNews.start_crawling, args=(global_task_queue, gueue_event))
    naver_news_thread.start()

    # 쓰레드가 종료된 후에 10분 뒤에 다시 실행
    threading.Timer(600, run_naver_news_parsing).start()


# doorNotification.py를 실행하는 함수
def run_door_notification():
    # 현재 시간
    current_time = datetime.now()

    # 현재 시간이 6 ~ 22 사이가 아니라면
    if current_time.time() > time(22, 0) or current_time.time() < time(6, 0):
        logger.info('Door: 22시 이후이기 때문에 6시까지 대기')
       # 목표 시간
        target_time = (current_time + timedelta(days=1)).replace(hour=6, minute=0, second=0)
        remaining_time = target_time - current_time
        print(remaining_time.total_seconds())
        # 6시까지 대기 후에 run_door_notification 함수 호출
        threading.Timer(remaining_time.total_seconds(), run_door_notification).start()
    else:
        logger.info('도어 알림 동작 시작')
        door_notification_thread = threading.Thread(target=doorNotification.run_door_crawling())
        door_notification_thread.start()
        logger.info('도어 알림 동작 완료 대기 전환')
        # 쓰레드가 종료된 후에 10분 뒤에 다시 실행
        threading.Timer(600, run_door_notification).start()
    
def print_queue():
    print(global_task_queue.qsize())
    threading.Timer(20, print_queue).start()


if __name__ == "__main__":
    setup_logger("system", "systemStarter.log") # logger 설정
    logger = logging.getLogger("system")
    logger.info('시스템 시작')

    # 가동시켜 놓으면 queue가 비어있으면 자동 대기상태로 들어감
    # run_news_briefbot()

    # 일정 시간마다 호출
    threading.Thread(target=run_naver_news_parsing).start()
    # threading.Thread(target=print_queue).start()
    # threading.Thread(target=run_door_notification).start()
