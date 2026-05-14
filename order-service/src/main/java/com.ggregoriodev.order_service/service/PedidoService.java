package com.ggregoriodev.order_service.service;

import com.ggregoriodev.order_service.entity.PedidoEntity;
import com.ggregoriodev.order_service.entity.PedidoStatus;
import com.ggregoriodev.order_service.exception.EventNotFoundException;
import com.ggregoriodev.order_service.repository.PedidoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoEntity criarPedido(PedidoEntity pedido) {
        pedido.setStatus(PedidoStatus.CRIADO);
        pedido.setCreatedAt(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    public List<PedidoEntity> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public void deletarPedido(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        pedidoRepository.deleteById(pedido.getId());
    }

    public PedidoEntity atualizarPedido(Long id, PedidoEntity pedidoAtualizado) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        pedido.setProduto(pedidoAtualizado.getProduto());

        return pedidoRepository.save(pedido);
    }

    public PedidoEntity buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);
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