# 예제 데이터
result = [
    ("user1", {"title": "공지 제목 1", "content": "공지 내용 1"}),
    ("user2", {"title": "공지 제목 2", "content": "공지 내용 2"}),
    ("user3", {"title": "공지 제목 3", "content": "공지 내용 3"}),
]

# 각 공지를 출력
for row in result:
    print("ID:", row[0])
    print("Title:", row[1]["title"])
    print("Content:", row[1]["content"])
    print()
