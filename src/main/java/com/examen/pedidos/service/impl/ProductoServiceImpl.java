package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.ProductoRequest;
import com.examen.pedidos.dto.response.ProductoResponse;
import com.examen.pedidos.entity.Producto;
import com.examen.pedidos.mapper.ProductoMapper;
import com.examen.pedidos.repository.ProductoRepository;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Inyección por constructor (SOLID)
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public BaseResponse<ProductoResponse> crearProducto(ProductoRequest request) {
        // Transformar DTO a Entidad
        Producto producto = productoMapper.toEntity(request);
        productoRepository.save(producto);

        // Retornar BaseResponse con el DTO de respuesta
        return BaseResponse.<ProductoResponse>builder()
                .codigo(201)
                .mensaje("Producto creado exitosamente")
                .objeto(productoMapper.toResponse(producto))
                .build();
    }


    @Override
    public BaseResponse <List<ProductoResponse>> listarProductos() {
        List<ProductoResponse> lista = productoRepository.findAll().stream()
                .map(productoMapper::toResponse)
                .collect(Collectors.toList());

        return BaseResponse.<List<ProductoResponse>>builder()
                .codigo(200)
                .mensaje("Lista de productos")
                .objeto(lista)
                .build();
    }

    @Override
    public BaseResponse <ProductoResponse> buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return BaseResponse.<ProductoResponse>builder()
                .codigo(200)
                .mensaje("Producto encontrado")
                .objeto(productoMapper.toResponse(producto))
                .build();
    }
}