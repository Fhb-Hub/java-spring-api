# Java Spring API

Este repositório contém uma API desenvolvida com Java e Spring Boot.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **MySQL**
- **Flyway** (migração de banco de dados)
- **Testcontainers** (testes de integração)
- **Rest Assured** (testes de API)
- **Swagger / OpenAPI**
- **JUnit / Mockito**

## Como Executar o Projeto

### Requisitos:

- Java 21+
- Maven
- Docker (opcional, para executar o banco de dados)

### Passos:

1. Clone o repositório:
   ```bash
   git clone https://github.com/Fhb-Hub/java-spring-api.git
   cd java-spring-api
   ```
2. Configure o banco de dados no arquivo `application.properties` ou `application.yml`
3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse a API:
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - API base: `http://localhost:8080`

## Executando Migrations do Flyway

Se precisar rodar as migrations manualmente, utilize os seguintes comandos:

```bash
mvn flyway:migrate
```

Para validar o estado das migrations:

```bash
mvn flyway:info
```

Se precisar desfazer a última migration aplicada:

```bash
mvn flyway:undo
```

## Testes

Para executar os testes unitários e de integração:

```bash
mvn test
```