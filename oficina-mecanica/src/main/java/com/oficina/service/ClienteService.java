package com.oficina.service;

import com.oficina.model.Cliente;
import com.oficina.repository.ClienteRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteService {
    private final ClienteRepository repository = new ClienteRepository();

    public Cliente cadastrar(String nome, String telefone) throws SQLException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        }
        Cliente cliente = new Cliente(nome, telefone);
        return repository.save(cliente);
    }

    public Optional<Cliente> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Cliente> listarTodos() throws SQLException {
        return repository.findAll();
    }

    public void atualizar(Cliente cliente) throws SQLException {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        }
        repository.update(cliente);
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
