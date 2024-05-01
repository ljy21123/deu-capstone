# 테스트 결과 쓰레드 생성에는 토큰소모 X
#
#
#

import embeddings
from openai import OpenAI
import time

apiKey = "sk-proj-dEbDsqUEIV4TmZFX71alT3BlbkFJySsmbKKAaU3chhiHnyGZ"

# 쓰레드 생성은 토큰 소모 X
client = OpenAI(api_key=apiKey)
news_thread = client.beta.threads.create()
ids = {'assistant_id': 'asst_3twHpxsoqsCeqUzu4QwJ6SYK', 'thread_id': f'{news_thread.id}'}


print("초기설정 완료", end="\n")

for i in range(2):
    print(i)
    msg = input()

    # 쓰레드에 메시지 추가 소모 X
    thread_message = client.beta.threads.messages.create(
        thread_id = ids['thread_id'],
        role="user", 
        content=msg,
    )
    print("thread_message:", thread_message, end="\n\n")

    # 메시지 실행 요청시 리퀘스트 횟수 +1
    run = client.beta.threads.runs.create(
        thread_id=ids['thread_id'],
        assistant_id=ids['assistant_id'],
    )
    print("run:", run, end="\n\n")


    while True:
        time.sleep(3)
        run = client.beta.threads.runs.retrieve(
            thread_id=ids['thread_id'],
            run_id=run.id
        )
        if run.status == "completed":
            print("답변 받음")
            break
        elif run.status == "failed":    
            print("요청 다씀")
            time.sleep(60)
            run = client.beta.threads.runs.create(
                thread_id=ids['thread_id'],
                assistant_id=ids['assistant_id'],
            )
        else :
            print("작업중인듯?")

    # 메시지 받아옴 요청 횟수 X
    thread_message = client.beta.threads.messages.list(ids['thread_id'],)
    print("답변:", thread_message.data[0].content[0].text.value)
    print()
    print("쓰레드 메시지 목록 출력")

    for message in thread_message.data:
            print(message.content[0].text.value)

em = embeddings.Embedding(apiKey=apiKey)
embed = em.getEmbedding("테스트")
print("\n임베딩 완료")