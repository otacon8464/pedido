package com.examen.pedidos.service;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.response.BaseResponse;

import java.util.List;

public interface ClienteService {
    BaseResponse<ClienteResponse> crearCliente(ClienteRequest request);

    BaseResponse<List<ClienteResponse>> listarClientes();

    BaseResponse<ClienteResponse> buscarPorId(Long id);
}