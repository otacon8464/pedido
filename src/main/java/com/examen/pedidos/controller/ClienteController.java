package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public BaseResponse<ClienteResponse> crear(@RequestBody ClienteRequest request) {
        return clienteService.crearCliente(request);
    }

    @GetMapping
    public BaseResponse<List<ClienteResponse>> listar() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public BaseResponse<ClienteResponse> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }
}