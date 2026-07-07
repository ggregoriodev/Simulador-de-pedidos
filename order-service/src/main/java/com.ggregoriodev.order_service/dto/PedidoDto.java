package com.ggregoriodev.order_service.dto;

import com.ggregoriodev.order_service.entity.PedidoEntity;
import com.ggregoriodev.order_service.entity.PedidoStatus;
import org.springframework.beans.BeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
public class PedidoDto {
    private Long id;
    private String produto;
    private PedidoStatus status;
    private LocalDateTime createdAt;
    public PedidoDto(PedidoEntity pedido) {
        BeanUtils.copyProperties(pedido, this);
    }
    public PedidoDto() {
    }


}
