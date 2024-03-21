# rest key: 11aa535784ec16106ad1f8e387d551ff
# https://kauth.kakao.com/oauth/authorize?client_id=11aa535784ec16106ad1f8e387d551ff&redirect_uri=https://example.com/oauth&response_type=code&scope=talk_message,friends
# doAZ1ykx7X4t5YkipG_uxCr6ITVbGCAQhOJS6sp1KsPtMfuBsEEzO2SVUbcKKcjaAAABjlUSoftDz1szkZmFRA

import requests

url = 'https://kauth.kakao.com/oauth/token'
rest_api_key = '11aa535784ec16106ad1f8e387d551ff'
redirect_uri = 'https://example.com/oauth'
authorize_code = 'doAZ1ykx7X4t5YkipG_uxCr6ITVbGCAQhOJS6sp1KsPtMfuBsEEzO2SVUbcKKcjaAAABjlUSoftDz1szkZmFRA'

data = {
    'grant_type':'authorization_code',
    'client_id':rest_api_key,
    'redirect_uri':redirect_uri,
    'code': authorize_code,
    }

response = requests.post(url, data=data)
tokens = response.json()
print(tokens)

# json 저장
import json
#1.
with open("myToken.json","w") as fp:
    json.dump(tokens, fp)
