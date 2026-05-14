# Simulador de Pedidos - Microservices

Projeto em desenvolvimento para simular um sistema de pedidos utilizando arquitetura de microsserviços.

> **Status:** Em construção

## Sobre o projeto

Este projeto tem como objetivo evoluir um sistema de pedidos para uma arquitetura orientada a eventos, separando responsabilidades entre serviços independentes.

Atualmente, o projeto está sendo estruturado com os seguintes microsserviços:

- **order-service**: responsável pelo gerenciamento dos pedidos.
- **notification-service**: responsável por consumir eventos de pedidos e futuramente enviar notificações.

## Arquitetura planejada

A ideia principal é que o `order-service` publique eventos relacionados aos pedidos em uma fila usando **RabbitMQ**.

O `notification-service` será responsável por consumir esses eventos e executar ações de notificação.

Fluxo planejado:
