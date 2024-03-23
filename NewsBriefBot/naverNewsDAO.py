# 뉴스 저장을 위한 DAO 
# 작성자: 양시현
# 수정 이력: 
# - 2024-03-23: 초기버전 생성

# import mysql.connector
import os
import logging

import article

class NaverNewsDAO:
    def __init__(self, host="127.0.0.1", user="root", password="1234", database="capstone") -> None:
        self.host = host
        self.user = user
        self.password = password
        self.database = database
        self.conn = None
        log_dir = os.path.join(os.path.dirname(__file__), '..', 'logs')
        self.setup_logger("naverNewsDAO", os.path.join(log_dir, "naverNewsDAO.log")) # logger 설정
        self.logger = logging.getLogger("system")
    
    def connect(self):
        try:
            self.conn = mysql.connector.connect(
                user = self.user, 
                password = self.password, 
                host = self.host,
                database = self.database
            )
            self.logger.info("MySQL DB에 연결되었습니다.")
        except mysql.connector.Error as err:
            self.logger.error(f"MySQL DB 연결 중 오류 발생:{err}")
            raise

    def disconnect(self):
        if self.conn:
            self.conn.close()
            self.logger.info("MySQL DB와 연결이 해제되었습니다.")
        else:
            self.logger.warning("MySQL DB와 연결이 이미 닫혔습니다.")

    def insert_news(self, news:article.Article):
        query = """
        INSERT INTO NaverNews (news_url, title, summary, original, image_url, publisher, created_at, updated_at, category)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
        """
        args = (news.url, news.title, news.summarized_news, news.original_news, news.img_url, news.newspaper, news.date, news.update_date, news.category)
        try:
            cursor = self.conn.cursor()
            cursor.execute(query, args)
            self.conn.commit()
            cursor.close()
            self.logger.info("새로운 뉴스가 추가되었습니다.")
        except mysql.connector.Error as err:
            self.logger.error(f"NaverNews 삽입 오류: {err}")
        
    def select_news(self):
        query = """
        SELECT original 
        FROM NaverNews
        WHERE DATE(created_at) = CURDATE();
        """
        try:
            cursor = self.conn.cursor(dictionary=True)
            cursor.execute(query)
            door_announcement = cursor.fetchone()
            cursor.close()
            return door_announcement
        except mysql.connector.Error as err:
            self.logger.error(f"NaverNews 조회 오류: {err}")

    def delete_news(self, id):
        try:
            cursor = self.conn.cursor()
            cursor.execute("DELETE FROM NaverNews WHERE id = %s", (id,))
            self.conn.commit()
            cursor.close()
            self.logger.info("NaverNews가 제거되었습니다.")
        except mysql.connector.Error as err:
            self.logger.error(f"NaverNews 삭제 오류: {err}")
        