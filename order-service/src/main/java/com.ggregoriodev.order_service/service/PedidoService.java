package com.ggregoriodev.order_service.service;

import com.ggregoriodev.order_service.dto.PedidoDto;
import com.ggregoriodev.order_service.entity.PedidoEntity;
import com.ggregoriodev.order_service.entity.PedidoStatus;
import com.ggregoriodev.order_service.exception.EventNotFoundException;
import com.ggregoriodev.order_service.publisher.RabbitMQProducer;
import com.ggregoriodev.order_service.repository.PedidoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final RabbitMQProducer rabbitMQProducer;

    public PedidoService(PedidoRepository pedidoRepository, RabbitMQProducer rabbitMQProducer) {
        this.pedidoRepository = pedidoRepository;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public PedidoDto criarPedido(PedidoDto pedido) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setProduto(pedido.getProduto());
        pedidoEntity.setStatus(PedidoStatus.CRIADO);
        pedidoEntity.setCreatedAt(LocalDateTime.now());

        PedidoEntity pedidoSalvo = pedidoRepository.save(pedidoEntity);

        rabbitMQProducer.enviamensagem("pedido criado");

        return new PedidoDto(pedidoSalvo);
    }

    public List<PedidoDto> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoDto::new)
                .toList();
    }

    public void deletarPedido(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        pedidoRepository.deleteById(pedido.getId());
    }

    public PedidoDto atualizarPedido(Long id, PedidoDto pedidoAtualizado) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        pedido.setProduto(pedidoAtualizado.getProduto());

        PedidoEntity pedidoSalvo = pedidoRepository.save(pedido);

        return new PedidoDto(pedidoSalvo);
    }

    public PedidoDto buscarPedidoPorId(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        return new PedidoDto(pedido);
    }

    @Scheduled(fixedRate = 30000)
    public void atualizarStatus() {
        List<PedidoEntity> pedidos = pedidoRepository.findAll();
        LocalDateTime agora = LocalDateTime.now();

        for (PedidoEntity pedido : pedidos) {
            if (pedido.getCreatedAt() == null) {
                continue;
            }

            long minutosPassados = Duration.between(pedido.getCreatedAt(), agora).toMinutes();

            if (pedido.getStatus() == PedidoStatus.CRIADO && minutosPassados >= 2) {
                pedido.setStatus(PedidoStatus.PREPARANDO);
            } else if (pedido.getStatus() == PedidoStatus.PREPARANDO && minutosPassados >= 4) {
                pedido.setStatus(PedidoStatus.ENTREGANDO);
            } else if (pedido.getStatus() == PedidoStatus.ENTREGANDO && minutosPassados >= 6) {
                pedido.setStatus(PedidoStatus.ENTREGUE);
            }
        }

        pedidoRepository.saveAll(pedidos);
    }
}