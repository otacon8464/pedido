package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.mapper.ClienteMapper; // Asegúrate de tener este mapper
import com.examen.pedidos.repository.ClienteRepository;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Inyección por constructor automática
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public BaseResponse <ClienteResponse> crearCliente(ClienteRequest request) {
        Cliente cliente = clienteMapper.toEntity(request);
        cliente.setFechaRegistro(LocalDateTime.now());
        clienteRepository.save(cliente);

        return BaseResponse.<ClienteResponse>builder()
                .codigo(201)
                .mensaje("Cliente registrado correctamente")
                .objeto(clienteMapper.toResponse(cliente))
                .build();
    }

    @Override
    public BaseResponse <List<ClienteResponse>> listarClientes() {
        List<ClienteResponse> lista = clienteRepository.findAll().stream()
                .map(clienteMapper::toResponse)
                .collect(Collectors.toList());

        return BaseResponse.<List<ClienteResponse>>builder()
                .codigo(200)
                .mensaje("Lista de clientes")
                .objeto(lista)
                .build();
    }

    @Override
    public BaseResponse <ClienteResponse> buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID:" +id));

        return BaseResponse.<ClienteResponse>builder()
                .codigo(200)
                .mensaje("Cliente encontrado")
                .objeto(clienteMapper.toResponse(cliente))
                .build();
    }
}