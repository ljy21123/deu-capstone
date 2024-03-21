import requests
import json


with open("myToken.json","r") as fp:
    tokens = json.load(fp)

url="https://kapi.kakao.com/v2/api/talk/memo/default/send"

# kapi.kakao.com/v2/api/talk/memo/default/send 

headers={
    "Authorization" : "Bearer " + tokens["access_token"]
}

data={
    "template_object": json.dumps({
        "object_type":"text",
        "text":"테스트!",
        "link":{
            "web_url":"www.naver.com"
        }
    })
}

print(1)
response = requests.post(url, headers=headers, data=data)
response.status_code