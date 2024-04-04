# 데이터 종합 및 요약 시스템을 시작하는 코드입니다.
# 작성자: 양시현
# 수정 이력: 
# - 2024-03-20: 초기버전 생성
# - 2024-03-20: 콘솔 출력이 아닌 로그 파일로 기록하도록 수정, 
# 해야할 일
# - 2024-03-23: 클래스로 리팩토링 수행
# - 뉴스 중복 여부 확인
# - DB연결

import threading
import queue
import logging
from datetime import datetime, time, timedelta
from time import sleep
import os

import naverNews
import newsBriefBot
import doorNotification

class System_starter:
    def __init__(self) -> None:
        self.global_task_queue = queue.Queue() # 작업을 관리할 큐
        self.gueue_event = threading.Event() # 이벤트 생성
        self.logger = None # 로거
        self.newsBot = None
        self.door = None
        self.naverNews = None

    def setup_logger(self, name, log_file, level=logging.INFO):
        formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s - %(message)s')
        handler = logging.FileHandler(log_file, encoding='utf-8')
        handler.setFormatter(formatter)

        self.logger = logging.getLogger(name)
        self.logger.setLevel(level)
        self.logger.addHandler(handler)

        # 콘솔에도 로그 출력
        console = logging.StreamHandler()
        console.setLevel(logging.INFO)
        formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s - %(message)s')
        console.setFormatter(formatter)
        logging.getLogger('').addHandler(console)

    # newsbriefbot.py를 실행하는 함수
    def run_news_briefbot(self):
        if not self.newsBot:
            self.newsBot = newsBriefBot.NewsBriefBot()
        news_briefbot_thread = threading.Thread(target=self.newsBot.run_brief, args=(self.global_task_queue, self.gueue_event))
        news_briefbot_thread.start()
        self.logger.info('뉴스 브리핑 봇 동작 시작')

    # naverNews.py를 실행하는 함수
    def run_naver_news_parsing(self):
        if not self.naverNews:
            self.naverNews = naverNews.NaverNews()
        self.logger.info('네이버 뉴스 크롤링 수행시작')
        naver_news_thread = threading.Thread(target=self.naverNews.start_crawling, args=(self.global_task_queue, self.gueue_event))
        naver_news_thread.start()

        # 쓰레드가 종료된 후에 10분 뒤에 다시 실행
        threading.Timer(600, self.run_naver_news_parsing).start()

    # doorNotification.py를 실행하는 함수
    def run_door_notification(self):
        if not self.door:
            self.door = doorNotification.DoorNotification()
        # 현재 시간
        current_time = datetime.now()

        # 현재 시간이 6 ~ 22 사이가 아니라면
        if current_time.time() > time(22, 0) or current_time.time() < time(6, 0):
            self.logger.info('Door: 22시 이후이기 때문에 6시까지 대기')
        # 목표 시간
            target_time = (current_time + timedelta(days=1)).replace(hour=6, minute=0, second=0)
            remaining_time = target_time - current_time
            self.logger.warning(f"{remaining_time.total_seconds()}초 만큼 대기합니다.")
            # 6시까지 대기 후에 run_door_notification 함수 호출
            threading.Timer(remaining_time.total_seconds(), self.run_door_notification).start()
        else:
            self.logger.info('도어 알림 동작 시작')
            door_notification_thread = threading.Thread(target=self.door.start())
            door_notification_thread.start()
            self.logger.info('도어 알림 동작 완료 대기 전환')
            # 쓰레드가 종료된 후에 10분 뒤에 다시 실행
            threading.Timer(600, self.run_door_notification).start()
        
    def print_queue(self):
        print("현재 큐의 사이즈:", self.global_task_queue.qsize())
        threading.Timer(20, self.print_queue).start()

if __name__ == "__main__":
    systemStart = System_starter()
    # 로그 설정
    log_dir = os.path.join(os.path.dirname(__file__), '..', 'logs')
    # 경로가 존재하지 않으면 생성
    if not os.path.exists(log_dir):
        os.makedirs(log_dir)
    systemStart.setup_logger("system", os.path.join(log_dir, "systemStarter.log")) # logger 설정
    systemStart.logger = logging.getLogger("system")
    systemStart.logger.info('시스템 시작')
    
    # 가동시켜 놓으면 queue가 비어있으면 자동 대기상태로 들어감
    systemStart.run_news_briefbot()

    # 일정 시간마다 호출
    threading.Thread(target=systemStart.run_naver_news_parsing).start()
    # threading.Thread(target=systemStart.print_queue).start()
    threading.Thread(target=systemStart.run_door_notification).start()
