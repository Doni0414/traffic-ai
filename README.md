# traffic-service

`traffic-service` — модуль информационной системы интеллектуального управления светофорным регулированием для оптимизации транспортного потока.

Сервис отвечает за:
- хранение топологии дорожной сети;
- хранение перекрёстков, рёбер, полос движения и телеметрии;
- предоставление REST API для создания и тестирования сущностей;
- расчёт рекомендуемого времени зелёного сигнала по `intersectionId`.

---

## Стек технологий

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Liquibase
- Spring Security OAuth2 Resource Server
- Springdoc OpenAPI / Swagger UI
- Keycloak
- Maven

---

## Требования

Для локального запуска нужны:

- Java 25
- Maven
- Docker
- PostgreSQL
- Keycloak

---

## Подготовка окружения

### 1. Запуск PostgreSQL в Docker

```bash
docker run --name traffic-postgres \
  -e POSTGRES_DB=traffic_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:14
```

### 2. Запуск Keycloak в Docker
```bash
docker run --name traffic-keycloak \
  -p 8080:8080 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:26.1.0 \
  start-dev
```

#### Создать realm

* client-id: traffic-ai
* Создать client для Swagger UI
* traffic-service-swagger-ui
* Рекомендуемые настройки client
* Client type: Public
* Standard flow: Enabled
* Valid redirect URIs
http://localhost:8081/swagger-ui.html
http://localhost:8081/swagger-ui/*
* Web origins
http://localhost:8081

### Сборка и запуск проекта
```bash
mvn clean package
mvn spring-boot:run
```

### Swagger-UI: http://localhost:8081/swagger-ui.html

### Тестирование
* Рекомендуемый порядок локального тестирования
* Запустить Keycloak
* Создать realm traffic-ai
* Создать client traffic-service-swagger-ui
* Запустить traffic-service
* Открыть Swagger UI
* Авторизоваться через Keycloak
* Создать тестовые lane
* Добавить тестовые traffic
* Вызвать endpoint расчёта по intersectionId