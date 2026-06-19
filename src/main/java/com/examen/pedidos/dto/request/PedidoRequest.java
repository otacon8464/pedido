package com.examen.pedidos.dto.request;


import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    private Long clienteId;
    private List<Item> items;

    @Getter
    @Setter
    public static class Item{
        private Long productoId;
        private Integer cantidad;
    }
}
