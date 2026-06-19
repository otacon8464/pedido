package com.examen.pedidos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private Long id;

    private String nombre;

    private String apellido;

    private Integer dni;

    private String correo;

    private LocalDateTime fechaRegistro;
}