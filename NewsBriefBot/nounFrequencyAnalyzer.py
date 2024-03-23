# 신문에 나오는 명사 빈도수 계산 및 시각화 클래스
# 작성자: 양시현
# 수정 이력: 
# - 2024-03-23: 초기버전 생성

from collections import Counter
from konlpy.tag import Okt
from wordcloud import WordCloud
import matplotlib.pyplot as plt
import naverNewsDAO
import logging
import os
from datetime import datetime

class NounFrequencyAnalyzer:
    def __init__(self) -> None:
        self.dao = naverNewsDAO.NaverNewsDAO()
        self.okt = Okt()
        self.nouns_list = []
        log_dir = os.path.join(os.path.dirname(__file__), '..', 'logs')
        self.setup_logger("nounFrequencyAnalyzer", os.path.join(log_dir, "nounFrequencyAnalyzer.log")) # logger 설정
        self.logger = logging.getLogger("system")

    def setup_logger(self, name, log_file, level=logging.INFO):
        formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s - %(message)s')
        handler = logging.FileHandler(log_file, encoding='utf-8')
        handler.setFormatter(formatter)

        self.logger = logging.getLogger(name)
        self.logger.setLevel(level)
        self.logger.addHandler(handler)

    def extract_nouns(self):
        self.dao.connect()
        news_data = self.dao.select_news()
        self.dao.disconnect()
        for news in news_data:
            temp = news['original']
            nouns = self.okt.nouns(temp)
            self.nouns_list.extend(nouns)
    
    def calculate_noun_frequency(self):
        noun_freq = {}
        for noun in self.nouns_list:
            if noun in noun_freq:
                noun_freq[noun] += 1
            else:
                noun_freq[noun] = 1
        sorted_noun_freq = sorted(noun_freq.items(), key=lambda x: x[1], reverse=True)
        return sorted_noun_freq


if __name__ == "__main__":
    images_dir = os.path.join(os.path.dirname(__file__), '..', 'images')
    font_file_path = os.path.join(os.path.dirname(__file__), 'font', 'malgun.ttf')
    current_time = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
    image_filename = f"wordcloud_{current_time}.png"
    noun = NounFrequencyAnalyzer()
    noun.extract_nouns()
    noun_frequencies = noun.calculate_noun_frequency()
    wordcloud = WordCloud(font_path=font_file_path,
                      background_color='white',width=1920, height=1080).generate_from_frequencies(dict(noun_frequencies))
    
    # 그림 그리기
    plt.figure(figsize=(10, 8))
    plt.imshow(wordcloud, interpolation='bilinear')
    plt.axis('off')

    # 그림 저장
    plt.savefig(os.path.join(images_dir, image_filename), bbox_inches='tight')
    

