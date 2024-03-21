import json
import requests


def refresh(existing_token:json):
    file_path = "kakao/root_token.json"
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
    with open(file_path, "w") as fp:
        json.dump(existing_token, fp)

    return existing_token