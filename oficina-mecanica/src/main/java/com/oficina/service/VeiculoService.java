package com.oficina.service;

import com.oficina.model.Veiculo;
import com.oficina.repository.ClienteRepository;
import com.oficina.repository.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VeiculoService {
    private final VeiculoRepository repository = new VeiculoRepository();
    private final ClienteRepository clienteRepository = new ClienteRepository();

    public Veiculo cadastrar(String placa, String modelo, int ano, int clienteId) throws SQLException {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória.");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Modelo do veículo é obrigatório.");
        }
        clienteRepository.findById(clienteId).orElseThrow(() ->
                new IllegalArgumentException("Cliente com id " + clienteId + " não encontrado."));
        Veiculo veiculo = new Veiculo(placa, modelo, ano, clienteId);
        return repository.save(veiculo);
    }

    public Optional<Veiculo> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Veiculo> listarTodos() throws SQLException {
        return repository.findAll();
    }

    public List<Veiculo> listarPorCliente(int clienteId) throws SQLException {
        return repository.findByClienteId(clienteId);
    }

    public void atualizar(Veiculo veiculo) throws SQLException {
        repository.update(veiculo);
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
