# Sistema de Pedidos - Arquitetura de Microsserviços

Sistema de gerenciamento de pedidos desenvolvido em **Java** utilizando **Spring Boot**, com arquitetura baseada em **microsserviços** e comunicação assíncrona através do **RabbitMQ**.

Este projeto foi criado com o objetivo de praticar conceitos fundamentais do ecossistema Spring, como desenvolvimento de APIs REST, persistência de dados, mensageria, testes e conteinerização com Docker.

---

# Arquitetura

O sistema é composto por dois microsserviços independentes:

### 📦 Order Service

Responsável pelo gerenciamento completo dos pedidos.

**Responsabilidades:**

* Criar pedidos
* Listar pedidos
* Buscar pedidos por ID
* Atualizar pedidos
* Excluir pedidos
* Alterar o status dos pedidos
* Publicar eventos no RabbitMQ após a criação de um pedido

---

### 📨 Notification Service

Responsável por consumir os eventos enviados pelo **Order Service** através do RabbitMQ.

**Responsabilidades:**

* Consumir mensagens da fila
* Processar notificações relacionadas aos pedidos
* Demonstrar comunicação assíncrona entre microsserviços

---

# Fluxo da aplicação

```text
Cliente
   │
   ▼
Order Service
   │
   ├── Salva pedido no banco
   │
   └── Publica mensagem
          │
          ▼
      RabbitMQ
          │
          ▼
Notification Service
```

---

# Tecnologias utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring AMQP
* RabbitMQ
* Maven
* Docker
* Docker Compose

---

# Funcionalidades

## Order Service

* ✅ Criar pedidos
* ✅ Listar pedidos
* ✅ Buscar pedido por ID
* ✅ Atualizar pedido
* ✅ Remover pedido
* ✅ Atualizar status
* ✅ Publicar eventos no RabbitMQ

## Notification Service

* ✅ Consumir mensagens do RabbitMQ
* ✅ Processar notificações de pedidos

---

# Status dos pedidos

Os pedidos podem assumir os seguintes estados:

| Status       | Descrição                    |
| ------------ | ---------------------------- |
| `CRIADO`     | Pedido registrado no sistema |
| `PREPARANDO` | Pedido em preparação         |
| `ENTREGANDO` | Pedido saiu para entrega     |
| `ENTREGUE`   | Pedido finalizado            |

---

# Como executar

## Pré-requisitos

* Java
* Maven
* Docker
* Docker Compose

---

## Clonar o projeto

```bash
git clone <url-do-repositorio>

cd nome-do-projeto
```

---

## Iniciar os serviços

```bash
docker-compose up -d
```

O Docker Compose iniciará todos os serviços necessários para a aplicação.

---

# Endpoints

## Criar pedido

```http
POST /pedidos
```

Body:

```json
{
  "produto": "Notebook"
}
```

---

## Listar pedidos

```http
GET /pedidos
```

---

## Buscar pedido

```http
GET /pedidos/{id}
```

---

## Atualizar pedido

```http
PUT /pedidos/{id}
```

Body:

```json
{
  "produto": "Mouse Gamer"
}
```

---

## Excluir pedido

```http
DELETE /pedidos/{id}
```

---

# Estrutura do projeto

```text
.
├── order-service
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   ├── entity
│   └── config
│
├── notification-service
│   ├── consumer
│   └── config
│
└── docker-compose.yml
```

---

# Conceitos praticados

Durante o desenvolvimento deste projeto foram aplicados conceitos importantes do ecossistema Java e Spring:

* Desenvolvimento de APIs REST
* Arquitetura em camadas
* Separação de responsabilidades
* DTOs
* Spring Data JPA
* Comunicação assíncrona
* RabbitMQ
* Microsserviços
* Docker e Docker Compose

---

# Objetivo

Este projeto foi desenvolvido como estudo prático para consolidar conhecimentos em:

* Java
* Spring Boot
* Microsserviços
* APIs REST
* RabbitMQ
* Docker
* Testes unitários

---

# Status do projeto

✅ Projeto concluído como primeira versão.

Apesar de ser um projeto de estudos, ele demonstra uma arquitetura baseada em microsserviços, comunicação assíncrona via RabbitMQ e boas práticas de organização utilizando Spring Boot.
