package com.examen.pedidos.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotNull(message = "El DNI es obligatorio")
    // Considero que un DNI suele tener 8 dígitos
    @Min(value = 10000000, message = "El DNI debe tener 8 dígitos")
    @Max(value = 99999999, message = "El DNI debe tener 8 dígitos")
    private Integer dni;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe proporcionar un formato de correo válido")
    private String correo;
}