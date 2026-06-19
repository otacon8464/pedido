package com.examen.pedidos.service;

import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.entity.*;
import com.examen.pedidos.exception.PedidoNotFoundException;
import com.examen.pedidos.exception.StockInsuficienteException;
import com.examen.pedidos.mapper.PedidoMapper;
import com.examen.pedidos.repository.*;
import com.examen.pedidos.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock private PedidoRepository pedidoRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private ProductoRepository productoRepository;
    @Mock private PedidoMapper pedidoMapper;

    @InjectMocks private PedidoServiceImpl pedidoService;

    @Test
    void crearPedido_cuandoDatosSonValidos_retornaPedidoCreado() {
        // Arrange
        PedidoRequest request = new PedidoRequest();
        request.setClienteId(1L);
        PedidoRequest.Item item = new PedidoRequest.Item();
        item.setProductoId(1L);
        item.setCantidad(2);
        request.setItems(List.of(item));

        Cliente cliente = Cliente.builder().id(1L).nombre("Daniel").apellido("Arana").build();
        Producto producto = Producto.builder().id(1L).nombre("Laptop").stock(10).precio(new BigDecimal("100.00")).build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(i -> i.getArguments()[0]);
        PedidoResponse pedidoResponse = PedidoResponse.builder()
                .total(new BigDecimal("200.00"))
                .build();

        when(pedidoMapper.toResponse(any(Pedido.class)))
                .thenReturn(pedidoResponse);

        // Act
        var response = pedidoService.crearPedido(request);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getCodigo());
        assertEquals("Pedido creado correctamente", response.getMensaje());
        assertEquals(new BigDecimal("200.00"), response.getObjeto().getTotal());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void crearPedido_cuandoStockEsInsuficiente_lanzaStockInsuficienteException() {
        // Arrange
        PedidoRequest request = new PedidoRequest();
        request.setClienteId(1L);
        PedidoRequest.Item item = new PedidoRequest.Item();
        item.setProductoId(1L);
        item.setCantidad(50); // Stock mayor al disponible
        request.setItems(List.of(item));

        Cliente cliente = new Cliente();
        Producto producto = Producto.builder().id(1L).stock(10).build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Act & Assert
        assertThrows(StockInsuficienteException.class, () -> pedidoService.crearPedido(request));
        verify(pedidoRepository, never()).save(any(Pedido.class));
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    void buscarPedido_cuandoNoExiste_lanzaPedidoNotFoundException() {

        // Arrange
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PedidoNotFoundException.class, () -> pedidoService.buscarPorId(1L));
        verify(pedidoRepository, times(1)).findById(1L);
    }
}