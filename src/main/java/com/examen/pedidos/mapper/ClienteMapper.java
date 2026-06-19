package com.examen.pedidos.mapper;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteRequest request) {
        return Cliente.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .dni(request.getDni())
                .correo(request.getCorreo())
                .build();
    }
        public ClienteResponse toResponse(Cliente entity) {
            return ClienteResponse.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .apellido(entity.getApellido())
                    .dni(entity.getDni())
                    .correo(entity.getCorreo())
                    .fechaRegistro(entity.getFechaRegistro())
                    .build();
        }
}
