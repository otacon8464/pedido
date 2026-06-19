package com.examen.pedidos.service;


import com.examen.pedidos.dto.request.ProductoRequest;
import com.examen.pedidos.dto.response.ProductoResponse;
import com.examen.pedidos.response.BaseResponse;

import java.util.List;

public interface ProductoService {
    BaseResponse<ProductoResponse> crearProducto(ProductoRequest request);
    BaseResponse<List<ProductoResponse>> listarProductos();
    BaseResponse<ProductoResponse> buscarPorId(Long id);
}
