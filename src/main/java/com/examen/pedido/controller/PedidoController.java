package com.examen.pedido.controller;

import com.examen.pedido.dto.request.PedidoRequest;
import com.examen.pedido.entity.Pedido;
import com.examen.pedido.response.BaseResponse;
import com.examen.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public BaseResponse crear(@RequestBody PedidoRequest pedidoRequest) {
        return pedidoService.crearPedido(pedidoRequest);
    }

    @GetMapping
    public List<BaseResponse> listar() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public BaseResponse buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

}
