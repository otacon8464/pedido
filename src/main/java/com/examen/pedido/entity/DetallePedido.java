package com.examen.pedido.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_pedidos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    // Relación con Producto (Muchos detalles tienen un producto)
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Relación con Pedido (necesaria para que el mappedBy de Pedido funcione)
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}