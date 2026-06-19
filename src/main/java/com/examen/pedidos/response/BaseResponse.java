package com.examen.pedidos.response;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class BaseResponse <T>{
    private Integer codigo;
    private String mensaje;
    private T objeto;

}
