package com.examen.pedido.service.impl;

import com.examen.pedido.dto.request.PedidoRequest;
import com.examen.pedido.entity.Pedido;
import com.examen.pedido.entity.Cliente;
import com.examen.pedido.response.BaseResponse;
import com.examen.pedido.repository.ClienteRepository;
import com.examen.pedido.repository.PedidoRepository;
import com.examen.pedido.service.PedidoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    // Inyección vía constructor (recomendado)
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public BaseResponse crearPedido(PedidoRequest pedidoRequest) {
        // 1. Buscar el cliente
        Cliente cliente = clienteRepository.findById(pedidoRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 2. Crear y configurar la entidad
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");
        pedido.setTotal(BigDecimal.valueOf(pedidoRequest.getTotal()));

        // 3. Guardar
        pedidoRepository.save(pedido);

        // 4. Retornar respuesta estándar
        return new BaseResponse("Pedido creado exitosamente con ID: " + pedido.getId(), 201);
    }

    @Override
    public List<BaseResponse> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(p -> new BaseResponse("Pedido ID: " + p.getId() + " - Estado: " + p.getEstado(), 200))
                .collect(Collectors.toList());
    }

    @Override
    public BaseResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        return new BaseResponse("Pedido encontrado: " + pedido.getEstado(), 200);
    }
}