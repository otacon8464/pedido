package com.examen.pedido.service;

import com.examen.pedido.entity.Producto;

import java.util.List;

public interface ProductoService {
    Producto crearProducto(Producto producto);
    List<Producto> listarProductos();
    Producto buscarPorId(Long id);
}
