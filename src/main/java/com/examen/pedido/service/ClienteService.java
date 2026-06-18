package com.examen.pedido.service;

import com.examen.pedido.entity.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Cliente buscarPorId(Long id);
}