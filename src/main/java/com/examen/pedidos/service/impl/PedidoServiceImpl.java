package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.entity.*;
import com.examen.pedidos.exception.PedidoNotFoundException;
import com.examen.pedidos.exception.StockInsuficienteException;
import com.examen.pedidos.mapper.PedidoMapper;
import com.examen.pedidos.repository.*;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Inyección de dependencias por constructor (SOLID)
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final PedidoMapper pedidoMapper;

    @Override
    @Transactional
    public BaseResponse<PedidoResponse> crearPedido(PedidoRequest pedidoRequest) {

        Cliente cliente = clienteRepository.findById(pedidoRequest.getClienteId())
                .orElseThrow(() -> new PedidoNotFoundException("Cliente no encontrado"));

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .fechaPedido(LocalDateTime.now())
                .estado("CREADO")
                .build();

        BigDecimal totalPedido = BigDecimal.ZERO;
        List<DetallePedido> detalles = new ArrayList<>();

        for (var item : pedidoRequest.getItems()) {
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new PedidoNotFoundException("Producto no encontrado"));

            if (item.getCantidad() <= 0)
                throw new RuntimeException("La cantidad debe ser mayor a cero");

            if (producto.getStock() < item.getCantidad())
                throw new StockInsuficienteException("Stock insuficiente para: " + producto.getNombre());


            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);


            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad()));
            totalPedido = totalPedido.add(subtotal);

            DetallePedido detalle = DetallePedido.builder()
                    .producto(producto)
                    .nombreProducto(producto.getNombre())
                    .cantidad(item.getCantidad())
                    .precioUnitario(producto.getPrecio())
                    .subTotal(subtotal)
                    .pedido(pedido)
                    .build();

            detalles.add(detalle);
        }


        pedido.setDetalles(detalles);
        pedido.setTotal(totalPedido);

        pedidoRepository.save(pedido);


        return BaseResponse.<PedidoResponse>builder()
                .codigo(201)
                .mensaje("Pedido creado correctamente")
                .objeto(pedidoMapper.toResponse(pedido))
                .build();
    }

    @Override
    public BaseResponse<PedidoResponse> buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("No se encontró el pedido"));

        return BaseResponse.<PedidoResponse>builder()
                .codigo(200)
                .mensaje("Pedido encontrado")
                .objeto(pedidoMapper.toResponse(pedido))
                .build();
    }

    @Override
    public BaseResponse<List<PedidoResponse>> listarPedidos() {
        List<PedidoResponse> lista = pedidoRepository.findAll().stream()
                .map(pedidoMapper::toResponse)
                .collect(Collectors.toList());

        return BaseResponse.<List<PedidoResponse>>builder()
                .codigo(200)
                .mensaje("Lista de pedidos")
                .objeto(lista)
                .build();
    }

    @Override
    public BaseResponse<List<PedidoResponse>> listarPedidosPorCliente(Long clienteId) {
        List<PedidoResponse> lista = pedidoRepository.findByClienteId(clienteId)
                .stream()
                .map(pedidoMapper::toResponse)
                .collect(Collectors.toList());

        return BaseResponse.<List<PedidoResponse>>builder()
                .codigo(200)
                .mensaje("Lista de pedidos del cliente")
                .objeto(lista)
                .build();
    }
}