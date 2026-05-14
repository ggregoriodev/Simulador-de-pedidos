package com.ggregoriodev.order_service.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException() {
        super("Pedido não encontrado");
    }
}