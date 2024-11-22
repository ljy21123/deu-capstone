# 📰 NewsSummarizer
## 💜 프로젝트 진행 기간
2024.03.11(월) ~ 2024.06.13(금) (94일간 진행)  
동의대 캡스톤 디자인II 프로젝트

## 목차
- [프로젝트 개요](#프로젝트-개요)
- [기술 스택](#기술-스택)
- [프로젝트 구조](#프로젝트-구조)
- [주요 기능](#주요-기능)
- [결과](#결과)
- [부록](#부록)

## 프로젝트 개요
NewsSummarizer는 다양한 뉴스 사이트에서 뉴스를 크롤링하고, 크롤링된 뉴스를 GPT 모델을 사용하여 요약하여 MySQL 데이터베이스에 저장한 후 웹으로 제공하는 시스템입니다.   
이 프로젝트는 Java Spring Boot와 Thymeleaf를 사용하여 웹 애플리케이션을 개발하였으며, Python3와 Selenium, Requests, OpenAI의 GPT-3.5 Turbo API와 Embedding API를 사용하여 뉴스 데이터를 수집하고 요약하며,
konlpy와 wordcloud를 사용하여 주요 명사를 추출하고 시각화하여 현재 주요 트랜드를 분석하였습니다.

## 기술 스택
- **Java Spring Boot**: 백엔드 웹 애플리케이션 프레임워크로, MVC 패턴을 활용한 웹 애플리케이션과 비즈니스 로직을 구현하는 데 사용됩니다.
- **Spring Security**: 인증, 권한 관리 그리고 데이터 보호 기능을 포함하여 사용자 관리 기능을 구현하는 데 사용됩니다.
- **JPA**: Java 애플리케이션에서 관계형 데이터베이스의 데이터를 관리하기 위한 표준 API입니다. Java 객체와 데이터베이스 테이블 간의 매핑을 처리합니다.
- **Thymeleaf**: 서버 측 자바 웹 애플리케이션 개발에서 사용되는 템플릿 엔진입니다.
- **Python 3**: 웹 크롤링과 데이터 처리를 위한 프로그래밍 언어로 사용됩니다.
- **Selenium**: 웹 브라우저 자동화를 통해 동적 웹 페이지의 데이터를 크롤링합니다.
- **Requests**: HTTP 요청을 보내고 응답을 처리하는 데 사용되는 Python 라이브러리입니다.
- **GPT-3.5 Turbo API**: 뉴스 기사를 요약하는 데 사용되는 자연어 처리 API입니다.
- **Embedding API**: 뉴스 기사의 텍스트 데이터를 벡터로 변환 후 DB에 저장하며, 사용자의 검색어를 임베딩 후 DB에 저장된 임베딩 정보와의 코사인유사도를 계산하여 검색의 정확도를 높입니다.
- **MySQL**: 뉴스 요약 정보를 저장하는 데 사용되는 관계형 데이터베이스 관리 시스템입니다.
- **Linux**: 서버 운영체제로 사용됩니다.

## 프로젝트 구조
![캡스톤디자인II_발표자료](https://github.com/user-attachments/assets/a5c9d6fc-d4e7-48cf-8659-502aff57cf6c)

## 주요 기능
- **뉴스 크롤링**: Selenium과 Requests를 사용하여 다양한 뉴스 사이트에서 뉴스 기사를 크롤링합니다.
- **뉴스 요약**: GPT-3.5 Turbo API를 사용하여 크롤링된 뉴스 기사를 요약합니다.
- **데이터베이스 저장**: MySQL 데이터베이스에 요약된 뉴스 기사를 저장합니다.
- **웹 인터페이스 제공**: Thymeleaf를 사용하여 뉴스 요약 정보를 사용자에게 제공하는 웹 인터페이스를 구현합니다.
- **자연어 검색**: Embedding API를 통해서 입력한 검색어를 벡터로 변환하여 DB에 저장된 뉴스의 벡터와 계산하여 유사한 데이터를 찾도록 합니다.
- **트렌드 분석**: 수집한 뉴스 정보들을 바탕으로 현재 주요 주제와 같은 정보를 시각화하여 제공합니다. 

## 결과
- **뉴스 요약**
![캡스톤디자인II_발표자료](https://github.com/user-attachments/assets/ac5e5317-af85-41f1-bf92-6f7e85645cc9)


- **트렌드 분석**
![wordcloud_2024-05-31](https://github.com/user-attachments/assets/ad25da25-39e4-42dd-b04c-afd257676cd8)

- **자연어 검색**
  - "카카오뱅크" 검색시 임베딩을 통해서 검색어와 제목간 유사도를 계산하며, 검색어와 일치하지 않아도 비슷한 의미를 가진 뉴스 목록이 출력됩니다.
![캡스톤디자인II_발표자료](https://github.com/user-attachments/assets/d42f27dc-71fc-43aa-a226-fe1e82420bcf)

- **그 외**
![캡스톤디자인II_발표자료](https://github.com/user-attachments/assets/7f880d06-5b13-47f2-81b8-7a688d06303f)


## 부록
- [결과보고서](./docs/캡스톤디자인II_결과보고서.hwp)
- [최종발표자료](./docs/캡스톤디자인II_발표자료.pptx)
