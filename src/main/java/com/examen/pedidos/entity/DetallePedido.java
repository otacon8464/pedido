package com.examen.pedidos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedidos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subTotal;

    // Relación con Producto (Muchos detalles tienen un producto)
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Relación con Pedido (necesaria para que el mappedBy de Pedido funcione)
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}