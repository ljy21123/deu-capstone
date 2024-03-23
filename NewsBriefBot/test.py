from datetime import datetime

# 주어진 문자열
date_str = '2024.03.22. 오전 7:02'

# 오전/오후와 시간을 분리
date_parts = date_str.split(' ')
time_str = date_parts[-1]  # 시간 부분

# 오후면 시간에 12시간을 더해줌
if '오후' in date_str:
    hour, minute = map(int, time_str.split(':'))
    hour += 12
    time_str = f"{hour:02d}:{minute:02d}"

# 시간 문자열을 datetime 객체로 변환
date_obj = datetime.strptime(f"{date_parts[0]} {time_str}", '%Y.%m.%d. %H:%M')

# 결과 출력
print(date_obj)
