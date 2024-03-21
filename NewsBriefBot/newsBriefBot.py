# GPT API를 통해 뉴스나 정보를 요약합니다!
# 작성자: 양시현
# 수정 이력: 
# - 2024-02-04: 초기버전 생성
 
import queue
import threading
import time
import logging
from openai import OpenAI # pip install openai

from article import Article


client = OpenAI(api_key="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")
logger = None

def setup_logger(name, log_file, level=logging.INFO):
    formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s - %(message)s')
    handler = logging.FileHandler(log_file, encoding='utf-8')
    handler.setFormatter(formatter)

    logger = logging.getLogger(name)
    logger.setLevel(level)
    logger.addHandler(handler)


def create_assistant() -> dict:
  # 어시던트 생성
  news_assistant = client.beta.assistants.create(
      name="NewsBriefBot",
      instructions="You're a news briefing assistant. Please answer in Korean. Please provide an objective summary of the news without including any personal opinions or emotional expressions. Please summarize the contents of the news in less than three lines.",
      model="gpt-3.5-turbo-1106",
  )

  # 쓰레드 생성
  news_thread = client.beta.threads.create()
  
  return {'assistant_id': news_assistant.id, 'thread_id': news_thread.id}


def brief(ids, msg):
	# 쓰레드에 메시지 추가
	thread_message = client.beta.threads.messages.create(
		thread_id = ids['thread_id'],
		role="user",
		content=msg,
	)

	# 메시지를 실행
	run = client.beta.threads.runs.create(
		thread_id=ids['thread_id'],
		assistant_id=ids['assistant_id'],
	)

	# 실행한 메시지의 결과가 생성되었는지 반복적으로 확인하며 대기
	while True:
		run = client.beta.threads.runs.retrieve(
			thread_id=ids['thread_id'],
			run_id=run.id
		)
		if run.status == "completed":
			# print(run)
			logger.info('정상적으로 요약값을 받았습니다.')
			break
		elif run.status == "failed":    
			logger.info('요청횟수를 모두 사용하여 대기합니다.')
			time.sleep(60)
			run = client.beta.threads.runs.create(
				thread_id=ids['thread_id'],
				assistant_id=ids['assistant_id'],
			)
		else :
			time.sleep(20)
			# print(run)

	thread_message = client.beta.threads.messages.list(ids['thread_id'],)
	return thread_message.data[0].content[0].text.value


def formatting_news(news:Article) -> str:
    """
    GPT에게 전달할 뉴스 형식을 생성합니다.
    """
    formatted_text = f"제목: {news.title}\n본문: {news.content}"
    return formatted_text


def run_brief(global_task_queue: queue.Queue, queue_event: threading.Event):
	setup_logger("newsBriefBot", "newsBriefBot.log") # logger 설정
	global logger 
	logger = logging.getLogger("newsBriefBot")
	logger.info('브리핑 봇 시작')

	# 최초 1번만 어시던트 생성 후 어시던트 및 쓰레드 id 반환
	#ids = create_assistant()
	ids = {'assistant_id': 'asst_CesgxDFMa4nyggTsNIFrZc3Y', 'thread_id': 'thread_cp8Be80FVGHSCoVgMGGatigM'}

	while True:
		# 메시지 실행
		queue_event.wait() # 이벤트 대기

		if not global_task_queue.empty():
			print("큐의 개수", global_task_queue.qsize())
			logger.info('뉴스 요약 수행')
			news: Article = global_task_queue.get()
			logger.info('큐에서 작업 획득 완료 요청 수행')
			temp = brief(ids, formatting_news(news))    
			logger.info('요약 요청 완료')
			logger.info("제목:"+news.title+"\n"+temp)
		else:
			logger.info('대기중인 작업이 없어 대기로 전환')
			queue_event.clear() # 대기상태로 전환
