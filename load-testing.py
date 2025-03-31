import requests
import random
import uuid
from concurrent.futures import ThreadPoolExecutor
import time

# URL do endpoint
url = 'http://localhost:8080/transactions'

# Função que faz uma requisição individual
def make_request():
    amount = random.randint(1, 100)  # Valor aleatório entre 1 e 100
    customer_uuid = str(uuid.uuid4())  # UUID aleatório

    data = {
        "amount": amount,
        "customerUuid": customer_uuid
    }

    headers = {
        'Content-Type': 'application/json'
    }

    try:
        response = requests.post(url, json=data, headers=headers)
        print(f"Status Code: {response.status_code}, Response: {response.text}")
    except Exception as e:
        print(f"Erro na requisição: {e}")

# Número total de chamadas e chamadas simultâneas
total_requests = 100
batch_size = 5

# Executor para gerenciar threads
with ThreadPoolExecutor(max_workers=batch_size) as executor:
    for i in range(0, total_requests, batch_size):
        futures = [executor.submit(make_request) for _ in range(batch_size)]
        # Aguarda a conclusão de todas as requisições no lote atual
        for future in futures:
            future.result()
        # Pausa opcional entre os lotes
        time.sleep(1)  # Aguarda 1 segundo entre os lotes
