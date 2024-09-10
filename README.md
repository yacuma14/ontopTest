# Docker
To install the containers, the following command must be executed in the root folder (docker-compose.yml)
 - `docker-compose up -d`

# Execute DB script
 - `src/main/resources/script_table.sql`

## Run application
mvn install

java -jar ontop-X.X.X.jar

## Configuration:  
 - `src/main/resources/application.properties`

## Swagger
 - `http://localhost:8081/swagger-ui/index.html#/`
 
## Enpoints
- `Execute transfer`

curl --location --request POST 'http://localhost:8081/api/v1/transaction/transfer' \
--header 'Content-Type: application/json' \
--data-raw '{
  "clientWallet": {
    "name": "pepe",
    "surname": "rodriguez"
  },
  "detailBank": {
    "routingNumber": "123456",
    "nationalIdentificationNumber": "123ABC",
    "accountNumber": "12345678901",
    "bankName": "citi"
  },
  "amountTransferRequest": {
    "shippingAmount": 45.5
  }
}'

- `get transactions by account client`

curl --location --request GET 'http://localhost:8081/api/v1/transaction/12345678901/get' \
--header 'Content-Type: application/json'


## External enpoints
- `get balance`

curl --location --request GET 'https://be08cc0f-a8f8-4e5f-995f-8e7e37835935.mock.pstmn.io/api/v1/wallet/1234567/balance' \
--data-raw ''

- `update balance`

curl --location --request PUT 'https://be08cc0f-a8f8-4e5f-995f-8e7e37835935.mock.pstmn.io/api/v1/wallet/1234567/balance' \
--header 'Content-Type: text/plain' \
--data-raw '{"accountOriginNumber":"123456789","amount":30}'

- `transfer payment`

curl --location --request POST 'https://be08cc0f-a8f8-4e5f-995f-8e7e37835935.mock.pstmn.io/api/v1/payment' \
--header 'Content-Type: text/plain' \
--data-raw '{"accountOriginNumber":"123456789","accountDestinyNumber":"666666666","amount":150}'
