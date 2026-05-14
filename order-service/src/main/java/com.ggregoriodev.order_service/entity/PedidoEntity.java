package com.ggregoriodev.order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String produto;

    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    private LocalDateTime createdAt;

    protected PedidoEntity() {
    }

    public PedidoEntity(String produto, PedidoStatus status) {
        this.produto = produto;
        this.status = status;
    }
}