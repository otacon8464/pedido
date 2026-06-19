package com.examen.pedidos.controller;

import com.examen.pedidos.dto.response.ProductoResponse;

import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ProductoService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;

    }

    @GetMapping
    public BaseResponse<List<ProductoResponse>> listar() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public BaseResponse<ProductoResponse> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }
}