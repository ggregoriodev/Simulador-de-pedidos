package com.ggregoriodev.order_service.repository;

import com.ggregoriodev.order_service.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}

