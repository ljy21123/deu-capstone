# 신문을 저장할 클래스를 정의합니다.
# 작성자: 양시현
# 수정 이력: 
# - 2024-02-04: 초기버전 생성


class Article:
    def __init__ (self, url, title, img_url, date, content, newspaper):
        # 뉴스 주소
        self.url = url
        # 뉴스 이미지 주소
        self.img_url = img_url
        # 뉴스 제목
        self.title = title
        # 뉴스 작성일
        self.date = date
        # 뉴스 본문
        self.content = content
        # 뉴스 작성사
        self.newspaper = newspaper

    # 객체 출력시 출력 형태
    def __str__(self):
        return f"주소: {self.url}, \n제목: {self.title}, \n날짜: {self.date}, \n신문사: {self.newspaper} \n이미지 주소: {self.img_url}"