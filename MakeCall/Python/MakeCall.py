import requests
from config import CONFIG
from requests.auth import HTTPBasicAuth
response = requests.post(CONFIG['url'], auth=HTTPBasicAuth(CONFIG['userName'], CONFIG['password']) , data={'number': CONFIG['number']})
print response.status_code
print response.text
