# InfinitePay Checkout

Microserviço Spring Boot responsável por criar checkouts na InfinitePay, receber o webhook de pagamento e consultar o status de uma transação.

## Tecnologias

- Java 21
- Spring Boot 3.5.5
- Spring Web, Validation e OpenFeign
- MongoDB para persistir os dados recebidos no webhook
- RabbitMQ para publicar eventos de pagamento aprovado
- Springdoc OpenAPI para documentação da API
- Maven

## Como o fluxo funciona

1. O cliente envia os dados do pedido para `POST /api/checkout/create`.
2. O serviço adiciona o `HANDLE` configurado e solicita à InfinitePay a criação do checkout.
3. A InfinitePay redireciona o cliente para a URL configurada e envia uma notificação para `POST /api/checkout/webhook`.
4. O webhook é salvo no MongoDB. Para cada `order_nsu`, o serviço evita duplicidade e publica um evento de pagamento aprovado no RabbitMQ.
5. O status pode ser consultado em `GET /api/checkout/status/{orderNsu}`.

## Pré-requisitos

- JDK 21 ou superior
- Maven 3.9 ou superior
- MongoDB acessível pela aplicação
- RabbitMQ acessível pela aplicação
- Credenciais e identificador da conta InfinitePay

## Configuração

A aplicação utiliza o perfil `dev` por padrão e lê as configurações abaixo como variáveis de ambiente:

| Variável | Descrição |
| --- | --- |
| `HANDLE` | Identificador da conta InfinitePay |
| `MONGODB_ATLAS_URI` | URI de conexão com o MongoDB |
| `RABBITMQ_HOST` | Host do RabbitMQ |
| `RABBITMQ_PORT` | Porta do RabbitMQ, normalmente `5672` |
| `RABBITMQ_USERNAME` | Usuário do RabbitMQ |
| `RABBITMQ_PASSWORD` | Senha do RabbitMQ |

Exemplo para desenvolvimento local:

```powershell
$env:HANDLE = "seu-handle"
$env:MONGODB_ATLAS_URI = "mongodb://localhost:27017/infinitepay_checkouts"
$env:RABBITMQ_HOST = "localhost"
$env:RABBITMQ_PORT = "5672"
$env:RABBITMQ_USERNAME = "guest"
$env:RABBITMQ_PASSWORD = "guest"
```

O arquivo `.env` é ignorado pelo Git. Não versione tokens, senhas ou URIs com credenciais.

## Executando a aplicação

```bash
mvn spring-boot:run
```

Para gerar o artefato:

```bash
mvn clean package
java -jar target/ms-infinitepay-checkout-1.0-SNAPSHOT.jar
```

Quando a aplicação estiver em execução, a documentação interativa está disponível em:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI: `http://localhost:8080/v3/api-docs`

## Endpoints

### Criar checkout

`POST /api/checkout/create`

Cria um checkout na InfinitePay e retorna a URL de pagamento.

```json
{
  "items": [
    {
      "quantity": 1,
      "price": 99.90,
      "description": "Produto de exemplo"
    }
  ],
  "order_nsu": "pedido-123",
  "redirect_url": "https://seu-dominio.com/api/checkout/redirect",
  "webhook_url": "https://seu-dominio.com/api/checkout/webhook",
  "customer": {
    "name": "Cliente Exemplo",
    "email": "cliente@example.com",
    "phone_number": "11999999999"
  },
  "address": {
    "cep": "01001000",
    "street": "Rua de Exemplo",
    "neighborhood": "Centro",
    "number": "100",
    "complement": "Sala 1"
  }
}
```

Resposta `200 OK`:

```json
{
  "checkout_url": "https://checkout.infinitepay.io/..."
}
```

Validações principais: `items`, `order_nsu`, `redirect_url`, `webhook_url`, `customer` e `address` são obrigatórios; a quantidade deve ser no mínimo `1`, o preço deve ser no mínimo `0.01` e a descrição deve ter entre 10 e 50 caracteres.

### Redirecionar após o checkout

`GET /api/checkout/redirect`

Recebe os parâmetros enviados pela InfinitePay após o checkout e responde com `302 Found`, redirecionando para `/api/checkout/status/{orderNsu}`.

Parâmetros: `receipt_url`, `order_nsu`, `slug`, `capture_method`, `transaction_nsu` e `transaction_id`.

### Receber webhook

`POST /api/checkout/webhook`

Recebe a confirmação de pagamento e retorna `200 OK` após persistir o primeiro webhook daquele `order_nsu`.

```json
{
  "invoice_slug": "minha-fatura",
  "amount": 99.90,
  "paid_amount": 99.90,
  "installments": 1,
  "capture_method": "credit_card",
  "transaction_nsu": "transacao-456",
  "order_nsu": "pedido-123",
  "receipt_url": "https://example.com/receipt/456",
  "items": [
    {
      "quantity": 1,
      "price": 99.90,
      "description": "Produto de exemplo"
    }
  ]
}
```

Webhooks repetidos para o mesmo `order_nsu` não são salvos novamente nem geram um novo evento.

### Consultar pagamento

`GET /api/checkout/status/{orderNsu}`

Consulta os dados do pagamento na InfinitePay usando as informações armazenadas do webhook.

Resposta `200 OK`:

```json
{
  "success": true,
  "paid": true,
  "amount": 99.90,
  "paid_amount": 99.90,
  "installments": 1,
  "capture_method": "credit_card"
}
```

Se não houver informações para o `orderNsu`, a API retorna `404 Not Found`. Dados inválidos retornam `400 Bad Request` com detalhes no formato `ProblemDetail`.

## RabbitMQ

Ao processar um webhook novo, a aplicação publica um evento JSON com os dados do pagamento:

| Item | Valor |
| --- | --- |
| Exchange | `payment.v1.events` |
| Tipo | Direct |
| Routing key | `payment.approved` |
| Fila | `payment.approved.queue` |

## Integrações externas

- Criação de checkout: `POST https://api.checkout.infinitepay.io/links`
- Consulta de pagamento: `POST https://api.infinitepay.io/invoices/public/checkout/payment_check`

## Testes

Os testes automatizados podem ser executados com:

```bash
mvn test
```

