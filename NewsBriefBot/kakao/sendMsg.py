import requests
import json


def sendMsg(msg:str, uuid:str, access_token:str):
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