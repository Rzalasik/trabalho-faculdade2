package com.oficina.controller;

import com.oficina.model.Cliente;
import com.oficina.service.ClienteService;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private final ClienteService service = new ClienteService();

    public Cliente cadastrar(String nome, String telefone) {
        try {
            Cliente cliente = service.cadastrar(nome, telefone);
            System.out.println("[Cliente] Cadastrado com sucesso: " + cliente);
            return cliente;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[Cliente] Erro ao cadastrar: " + e.getMessage());
            return null;
        }
    }

    public void listarTodos() {
        try {
            List<Cliente> clientes = service.listarTodos();
            System.out.println("[Cliente] Total de clientes: " + clientes.size());
            clientes.forEach(c -> System.out.println("  " + c));
        } catch (SQLException e) {
            System.out.println("[Cliente] Erro ao listar: " + e.getMessage());
        }
    }
}
