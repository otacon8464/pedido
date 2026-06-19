package com.examen.pedidos.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotEmpty(message = "El pedido debe tener al menos un producto")
    @Valid // IMPORTANTE: Esto asegura que se validen los campos dentro de la clase Item
    private List<Item> items;

    @Getter
    @Setter
    public static class Item {
        @NotNull(message = "El ID del producto es obligatorio")
        private Long productoId;

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a cero")
        private Integer cantidad;
    }
}