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
