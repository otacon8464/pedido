package com.examen.pedido.service;

import com.examen.pedido.dto.request.PedidoRequest;
import com.examen.pedido.dto.response.PedidoResponse;
import com.examen.pedido.response.BaseResponse;

import java.util.List;


public interface PedidoService {
    BaseResponse crearPedido(PedidoRequest pedidoRequest);
    List<BaseResponse> listarPedidos();
    BaseResponse buscarPorId(Long id);
}
