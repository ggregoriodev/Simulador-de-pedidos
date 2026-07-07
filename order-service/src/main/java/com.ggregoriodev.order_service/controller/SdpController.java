package com.ggregoriodev.order_service.controller;

import com.ggregoriodev.order_service.dto.PedidoDto;
import com.ggregoriodev.order_service.entity.PedidoEntity;
import com.ggregoriodev.order_service.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class SdpController {

    private final PedidoService pedidoService;

    public SdpController(PedidoService pedidoService) {

        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDto> listarPedidos() {

        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoDto buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @PostMapping
    public PedidoDto adicionarPedido(@RequestBody PedidoDto pedido) {
        return pedidoService.criarPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
    }

    @PutMapping("/{id}")
    public PedidoDto atualizarPedido(@PathVariable Long id, @RequestBody PedidoDto pedido) {
        return pedidoService.atualizarPedido(id, pedido);
    }
}