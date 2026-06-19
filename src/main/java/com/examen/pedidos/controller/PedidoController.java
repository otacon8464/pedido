package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<BaseResponse<PedidoResponse>> crear(@Valid @RequestBody PedidoRequest request) {
        return ResponseEntity.status(201).body(pedidoService.crearPedido(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PedidoResponse>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<PedidoResponse>>> listar() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<BaseResponse<List<PedidoResponse>>> listarPedidosPorCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(
                pedidoService.listarPedidosPorCliente(clienteId));
    }

}