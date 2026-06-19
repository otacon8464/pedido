package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<BaseResponse<ClienteResponse>> crear(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.status(201).body(clienteService.crearCliente(request));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ClienteResponse>>> listar() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ClienteResponse>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
}