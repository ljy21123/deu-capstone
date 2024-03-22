# rest key: 11aa535784ec16106ad1f8e387d551ff
# authorize code를 받아오기 위한 주소로 알림을 받기 위해서는 접속이 필요
# https://kauth.kakao.com/oauth/authorize?client_id=11aa535784ec16106ad1f8e387d551ff&redirect_uri=https://example.com/oauth&response_type=code&scope=talk_message,friends
# authorize code: 8-nuN1BF1s99gI7Wtqp5If4TVtrHhZVi-TwS_GKIChCFodAbmNvXgIfhCLgKPXTZAAABjlbSGaQhI_W2iNNaeg
import requests
import json
import os
import logging

class KakaoMsg:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            log_dir = os.path.join(os.path.dirname(__file__), '..', '..', 'logs')
            cls._instance.setup_logger("kakao", os.path.join(log_dir, "kakaoMsg.log")) # logger 설정
            cls._instance.logger = logging.getLogger("kakao")
            cls._instance.logger.info('카카오 객체 생성')
        return cls._instance
    
    def setup_logger(self, name, log_file, level=logging.INFO):
        formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s - %(message)s')
        handler = logging.FileHandler(log_file, encoding='utf-8')
        handler.setFormatter(formatter)

        self.logger = logging.getLogger(name)
        self.logger.setLevel(level)
        self.logger.addHandler(handler)

    def refresh(self, existing_token:json):
        kakao_path = os.path.dirname(__file__)
        files_path = os.path.join(kakao_path, "files")
        root_token_path = os.path.join(files_path, "root_token.json")
        rest_api_key = '11aa535784ec16106ad1f8e387d551ff'
        url = "https://kauth.kakao.com/oauth/token"
        data = {
            "grant_type": "refresh_token",
            "client_id": rest_api_key,
            "refresh_token": existing_token['refresh_token']
        }
        response = requests.post(url, data=data)
        new_token = response.json()
        existing_token['access_token'] = new_token['access_token']

        # kakao_code.json 파일 저장
        with open(root_token_path, "w") as fp:
            json.dump(existing_token, fp)

        return existing_token

    def getFriendList(self, tokens:json) -> json:
        # 친구목록을 요청하기 위한 headers 및 params
        url = "https://kapi.kakao.com/v1/api/talk/friends"
        access_token = tokens['access_token']
        params = {"limit": 10}
        headers = {"Authorization": "Bearer " + access_token}

        response = requests.get(url, headers=headers, params=params)

        return response.json()

    def main(self):
        kakao_path = os.path.dirname(__file__)
        files_path = os.path.join(kakao_path, "files")
        root_token_path = os.path.join(files_path, "root_token.json")
        friend_list_path = os.path.join(files_path, "friend_list.json")
        # 파일이 존재한다면 
        if os.path.exists(root_token_path):
            print("기존의 데이터로 수행")
            with open(root_token_path, "r") as fp:
                tokens = json.load(fp)
        # 파일이 없다면 수행
        else:
            print("정보가 존재하지 않습니다.")
            print("주소에 접속 후 code= 뒤의 문자열을 입력해 주세요")
            print("https://kauth.kakao.com/oauth/authorize?client_id=11aa535784ec16106ad1f8e387d551ff&redirect_uri=https://example.com/oauth&response_type=code&scope=talk_message,friends")
            authorize_code = input()
            # access_token, 등을 받다오기 위한 주소
            url = 'https://kauth.kakao.com/oauth/token'
            rest_api_key = '11aa535784ec16106ad1f8e387d551ff'
            redirect_uri = 'https://example.com/oauth'
            authorize_code = '8-nuN1BF1s99gI7Wtqp5If4TVtrHhZVi-TwS_GKIChCFodAbmNvXgIfhCLgKPXTZAAABjlbSGaQhI_W2iNNaeg'
            
            # 요청 데이터 생성
            data = {
                'grant_type':'authorization_code',
                'client_id':rest_api_key,
                'redirect_uri':redirect_uri,
                'code': authorize_code,
            }

            # 요청을 보낸 후 결과를 변수에 저장
            response = requests.post(url, data=data)
            # json으로 변환 후 출력
            tokens = response.json()
            print("\n친구목록")
            print(tokens)

            # json 저장
            with open(root_token_path,"w") as fp:
                json.dump(tokens, fp)

        # JSON 형태로 변환
        friend_list = self.getFriendList(tokens)
        print(friend_list,"\n")

        try:
            friend_list['code']
            print("access_token을 다시 받아와야함\n")
            self.refresh(tokens)
            friend_list = self.getFriendList(tokens)
            print("새로 할당된 토큰\n", tokens, "\n")
            print(friend_list,"\n")

        except KeyError:
            print("access_token 유효")

        with open(friend_list_path, 'w') as fp:
            json.dump(friend_list, fp)

        return tokens, friend_list

    def sendMsg(self, msg:str, uuid:str, access_token:str):
        # 헤더 생성
        headers={"Authorization" : "Bearer " + access_token}
        # 전송할 url
        send_url= "https://kapi.kakao.com/v1/api/talk/friends/message/default/send"
        # 전송할 데이터
        data={
            'receiver_uuids': '["{}"]'.format(uuid),
            "template_object": json.dumps({
                "object_type":"text",
                "text":msg,
                "link":{
                    "web_url":"http://door.deu.ac.kr/",
                },
                "button_title": "확인"
            })
        }

        # 전송
        response = requests.post(send_url, headers=headers, data=data)
        # 코드
        return response.status_code
    
    def sendMessageToKakao(self, msg="ㅁㄴㅇㄹ", name="이준영"):
        token, friend_list = self.main() # 메세지를 전송할 준비
        
        found = False

        # 친구 목록에서 이름을 찾아 그 사람의 uuid를 전송
        for element in friend_list['elements']:
            if element['profile_nickname'] == name:
                code = self.sendMsg(msg, element['uuid'], token['access_token'])
                print(code)
                found = True
                if code == 200:
                    print("code 200: 정상 전송")
                elif code == 400:
                    print("code 400: 잘못된 요청")
                
                break

        if not found:
            # 해당 이름을 찾지 못한 경우 실행할 코드
            print("해당 이름을 찾을 수 없습니다.")

    