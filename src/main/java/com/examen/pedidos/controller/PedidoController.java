package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {


    private final PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public BaseResponse<PedidoResponse> crear(@RequestBody PedidoRequest pedidoRequest) {
        return pedidoService.crearPedido(pedidoRequest);
    }

    @GetMapping
    public BaseResponse<List<PedidoResponse>> listar() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public BaseResponse<PedidoResponse> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

}
