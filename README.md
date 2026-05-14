# 📦 Simulador de Pedidos - Microservices

Sistema de simulação e gerenciamento de pedidos desenvolvido com arquitetura de microsserviços.

## 🚀 Objetivo do Projeto

Este projeto foi criado com o objetivo de estudar e aplicar conceitos modernos de desenvolvimento back-end, incluindo:

* Arquitetura de Microsserviços
* APIs REST
* Comunicação entre serviços
* Docker
* Mensageria
* Processamento assíncrono

## 🛠️ Tecnologias Utilizadas

* Java
* Spring Boot
* Maven
* Docker
* MySQL
* RabbitMQ
* Git/GitHub

## ⚡ Funcionalidades

* Criação de pedidos
* Atualização de status
* Comunicação entre microsserviços
* Estrutura preparada para escalabilidade

## 🔄 Em Desenvolvimento

Atualmente estou desenvolvendo a parte de notificações do sistema.

A próxima evolução do projeto será:

* 📧 Envio automático de e-mails
* 📨 Integração com RabbitMQ
* 🔔 Sistema de eventos para atualização de pedidos
* ⚡ Comunicação assíncrona entre microsserviços

A ideia é que, sempre que um pedido for atualizado, um evento seja enviado pelo RabbitMQ e o serviço de notificações processe esse evento para enviar um e-mail automaticamente ao usuário.

## ▶️ Como Executar

Clone o repositório:

```bash
git clone https://github.com/ggregoriodev/Simulador-de-pedidos.git
```

Entre na pasta:

```bash
cd Simulador-de-pedidos
```

Execute os serviços:

```bash
docker-compose up
```

## 📚 Objetivos de Estudo

Esse projeto também serve como laboratório para aprofundamento em:

* Microsserviços
* RabbitMQ
* Arquitetura orientada a eventos
* Docker
* Comunicação entre serviços
* Escalabilidade de sistemas

## 👨‍💻 Autor

Desenvolvido por Guilherme Gregorio.

GitHub: https://github.com/ggregoriodev
