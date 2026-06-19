package com.examen.pedidos.mapper;

import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.entity.Pedido;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class PedidoMapper {

    public PedidoResponse toResponse(Pedido pedido){
        return PedidoResponse.builder()
                .id(pedido.getId())
                .cliente(pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido())
                .total(pedido.getTotal())
                .estado(pedido.getEstado())
                .fechaPedido(pedido.getFechaPedido())

                .detalles(
                        pedido.getDetalles().stream()
                                .map(detalle -> PedidoResponse.DetalleResponse.builder()
                                        .productoId(detalle.getProducto().getId())
                                        .nombreProducto(detalle.getNombreProducto())
                                        .cantidad(detalle.getCantidad())
                                        .precioUnitario(detalle.getPrecioUnitario())
                                        .subtotal(detalle.getSubTotal())
                                        .build())
                                .toList()
                )


                .build();
    }

}
