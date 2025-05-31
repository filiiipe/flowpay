# FlowPay

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](#)

*Um endpoint REST para controle de lançamentos de débito e crédito e cálculo de saldo diário.*

---

## Índice
1. [Visão Geral](#visão-geral)
2. [Clonar o Repositório](#clonar-o-repositório)
3. [Build](#build)
4. [Configuração](#configuração)
5. [Execução](#execução)
6. [Endpoints](#endpoints)
7. [Decisões de Design](#decisões-de-design)
8. [Testes](#testes)
9. [Próximos Passos](#próximos-passos)

---

## Visão Geral
FlowPay é uma aplicação Spring Boot que permite registrar lançamentos financeiros (débito/crédito) e consultar o saldo diário de forma consolidada via API REST, retornando JSON.

---

## Clonar o Repositório
```bash
git clone https://github.com/seu-usuario/flowpay.git
cd flowpay
```

---

## Build
Compile e empacote o projeto com Maven:
```bash
mvn clean install
```

---

## Configuração
Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:flowpaydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

> **Dica:** Para MySQL/PostgreSQL, ajuste `spring.datasource.url`, `driverClassName`, `username`, `password` e `spring.jpa.database-platform` conforme seu ambiente.

---

## Execução
- **Via Maven**:
  ```bash
  mvn spring-boot:run
  ```
- **Via JAR**:
  ```bash
  java -jar target/flowpay-0.0.1-SNAPSHOT.jar
  ```

A API estará disponível em `http://localhost:8080`.

---

## Endpoints

### Criar Lançamento
`POST /entries`

**Payload (JSON)**:
```json
{
  "dataDTO": "2025-04-23",
  "type": "CREDIT",
  "amount": 1000.00,
  "description": "Pagamento recebido"
}
```

**Exemplo com cURL**:
```bash
curl -X POST http://localhost:8080/entries \
  -H "Content-Type: application/json" \
  -d '{"dataDTO":"2025-04-23","type":"CREDIT","amount":1000.00}'
```

### Consultar Saldo Diário
`GET /entries/daily-balance`

**Resposta (JSON)**:
```json
[
  {
    "dataDTO": "2025-04-23",
    "totalCredit": 1000.00,
    "totalDebit": 250.00,
    "finalBalance": 750.00
  }
]
```

---

## Decisões de Design
- **Estrutura MVC**: separação clara entre `controller`, `service`, `repository`, `model` e `dto`.
- **JPA/H2**: uso de Spring Data JPA, H2 em memória para testes rápidos e sem configuração externa.
- **DTO**: `DailyBalanceDTO` para formatar saída JSON sem acoplar entidade ao cliente.
- **Enum**: `EntryType` (`CREDIT`, `DEBIT`) garante consistência de tipo de lançamento.
- **Validações & Precisão**: uso de `LocalDate` para dataDTO pura e `BigDecimal` para valores.

---

## Testes
### Unitários
- **Service e Controller**:
  ```bash
  mvn test -Dtest=EntryServiceTest,EntryControllerTest
  ```

### Integração de Repositório
- **EntryRepositoryTest** (H2 em memória):
  ```bash
  mvn test -Dtest=EntryRepositoryTest
  ```

### Todos os Testes
```bash
mvn test
```

---

## Próximos Passos
- Migrar para banco relacional em produção (PostgreSQL/MySQL).
- Implementar autenticação JWT e documentação Swagger.
- Adicionar monitoramento com Spring Boot Actuator.
- Configurar CI/CD (GitHub Actions, Jenkins, etc.).

---

© 2025 FlowPay.

