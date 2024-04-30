# 자연어 검색을 위한 임베딩 모델입니다.
# 작성자: 양시현
# 수정 이력: 
# - 2024-04-26: 초기버전 생성

import numpy as np
from numpy.linalg import norm
from openai import OpenAI
import json

class Embedding:
    def __init__(self, apiKey) -> None:
        self.result = None
        self.client = OpenAI(api_key=apiKey)

    def getEmbedding(self, text="") -> list:
        model="text-embedding-3-small"
        response = self.client.embeddings.create(
            input = [text], 
            model=model
        ).data[0].embedding
        
        # print(response)
        jsonEmbedding = json.dumps(response)
        # print(jsonEmbedding)
        return jsonEmbedding
    
    # 코사인 거리 계산
    def cosineDist(this, a, b):
        return 1 - np.dot(a,b) / (norm(a)*norm(b))


searchWord = "채상병 판결 결과"
# searchEmb = ""
# embeddings = np.loadtxt('my.txt')
# print(embeddings.dtype)
# texts = ["이재명 “채 상병 특검은 국민의 명령” 회담 의제 공식화",
#          "조국혁신당 이재명, 범야권 대표로 영수회담 가야···답 주셨으면",
#          "내일 영수회담 2차 실무회동…의제 두고 신경전[투나잇이슈]"]

a = Embedding("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")
searchEmb = a.getEmbedding(searchWord)

# for i in texts:
#     embeddings.append(a.getEmbedding(i))
#     print(len(embeddings))

# np.savetxt('my.txt', embeddings)

# for i in range(0, len(embeddings)):
#     print(a.cosineDist(searchEmb, embeddings[i]), texts[i])
