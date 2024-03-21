# 네이버 뉴스를 파싱하여 뉴스 객체를 생성합니다.
# 작성자: 양시현
# 수정 이력: 
# - 2024-02-04: 초기버전 생성
# - 2024-03-20: 콘솔 출력이 아닌 로그 파일로 기록하도록 수정

from urllib.parse import urljoin
from bs4 import BeautifulSoup # pip install beautifulsoup4
import requests # pip install requests requests
from datetime import datetime
import re
import threading
import queue
import logging

import newsBriefBot
from article import Article


def setup_logger(name, log_file, level=logging.INFO):
    formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s - %(message)s')
    handler = logging.FileHandler(log_file, encoding='utf-8')
    handler.setFormatter(formatter)

    logger = logging.getLogger(name)
    logger.setLevel(level)
    logger.addHandler(handler)


def make_url(category, subcategory, date) -> str:
    """
    네이버 뉴스의 카테고리는 
    정치, 경제, 사회 생활/문화, IT/과학, 세계 6개로 나뉜 후 
    세부 항목으로 나뉜다
    """

    # 검색할 날짜 20240101 형식
    temp_date = datetime.now()
    search_date = temp_date.strftime("%Y%m%d")
    search_date = 20240313
    # 검색할 분류
    category = 105 # IT/과학
    #category = 103 # 생활/문화
    # 검색할 세부 분류
    #subcategory = 731 # 모바일
    subcategory = 227 # 통신/뉴미디어
    #subcategory = 243 # 책
    
    url = "https://news.naver.com/breakingnews/section/" + str(category) + "/" + str(subcategory) + "?date=" + str(search_date)

    return url


# ConnectionError방지
headers = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/98.0.4758.102"}

def articles_crawler(url):
    """
    주소에 존재하는 뉴스 링크들을 전부 가져온 후 배열로 반환
    """
    # print("최신 뉴스 링크를 추출하는 중...")
    original_html = requests.get(url, headers=headers)
    html = BeautifulSoup(original_html.text, "html.parser")
    url_naver = html.select('.sa_text_title')
    # print("최신 뉴스 링크를 추출 완료")
    urls = []
    
    for link in url_naver:
        urls.append(urljoin("https://news.naver.com", link['href']))
    
    return urls


def print_news(url) -> Article:
    """
    뉴스의 주소, 제목, 작성일, 본문, 신문사 반환
    """

    # 뉴스 html 크롤링
    original_html = requests.get(url, headers=headers)
    # 크롤링한 데이터의 본문
    html = BeautifulSoup(original_html.text, "html.parser")
    
    # 크롤링 데이터 저장
    # with open("output.html", "w", encoding="utf-8") as html_file:
    #     html_file.write(str(html))
    
    # 제목 파싱
    news_title = html.title.get_text(strip=True)
    
    # 날짜 파싱
    news_dates = html.find_all('div', class_='media_end_head_info_datestamp_bunch')

    date_text = ''
    if len(news_dates) == 2:
        date = news_dates[0].find('span').get_text(strip=True)
        modify_date = news_dates[1].find('span').get_text(strip=True)
        date_text = '작성일 ' + date + ' 수정일 ' + modify_date
    else:
        date_text = '작성일 ' + news_dates[0].find('span').get_text(strip=True)

    # 신문사 파싱
    # 정규 표현식을 사용하여 office 객체 추출
    office_match = re.search(r'name\s*:\s*["\'](.*?)["\']', str(html))

    # 객체가 존재하는지 확인
    if office_match:
        office_json = str(office_match.group(1))
    else:
        office_json = ''

    # newspaper = html.select_one('.media_end_head_top_channel_layer_text strong').get_text(strip=True)

    # 기사 본문 파싱
    content = html.select_one('.newsct_article._article_body').get_text(strip=True)

    # 이미지 처리
    newsct_body = html.find('div', class_='newsct_article _article_body')
    img_tag = newsct_body.find('img')
    img_url = None

    # 이미지 존재 유무 처리
    if img_tag:
        img_url = img_tag['data-src']
        # print(img_url)
    # else:
    #     print("img 태그가 존재하지 않습니다.")

    return Article(url, news_title, img_url, date_text, content, office_json)


def start_crawling(global_task_queue: queue.Queue, queue_event: threading.Event):
    setup_logger("naverNews", "naverNews.log")  # Sub logger 설정
    logger = logging.getLogger("naverNews")
    logger.info('네이버 뉴스 크롤링 시작')
    
    logger.info('분야에 맞는 URL 생성')
    url = make_url(1, 1, 1)
    logger.info(url + ' 링크 생성 완료')
    
    logger.info('해당 분야의 뉴스 목록 가져오는중...')
    urls = articles_crawler(url)
    news_list = []
    logger.info('뉴스 목록 가져오기 완료!')

    logger.info('뉴스 내용 파싱 시작')
    for i in urls:
        # 뉴스 객체 반환
        news = print_news(i)
        news_list.append(news)
        # 큐에 작업 추가
        global_task_queue.put(news)
        # 대기중인 브리핑 봇을 깨움
        queue_event.set()

    logger.info('파싱 완료!')


# if __name__ == "asfadfasf":
#     if len(news) == 0:
#         print('현재 새로운 뉴스가 없습니다.')
#     else:
#         print('gpt의 답변을 생성하고 있습니다.')
#         for i in news:
#             result = newsBriefBot.run(formatting_news(i))
#             print("제목: " + i.title)
#             print(result)
#             print("주소: " + i.url)
#             print()