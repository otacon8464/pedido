package com.examen.pedidos.service;

import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.response.BaseResponse;

import java.util.List;


public interface PedidoService {
    BaseResponse<PedidoResponse> crearPedido(PedidoRequest pedidoRequest);
    BaseResponse<PedidoResponse> buscarPorId(Long id);
    BaseResponse<List<PedidoResponse>> listarPedidos();
}
