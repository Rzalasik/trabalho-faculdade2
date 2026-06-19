package com.oficina.service;

import com.oficina.model.OrdemServico;
import com.oficina.repository.OrdemServicoRepository;
import com.oficina.repository.VeiculoRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrdemServicoService {
    private final OrdemServicoRepository repository = new OrdemServicoRepository();
    private final VeiculoRepository veiculoRepository = new VeiculoRepository();

    public OrdemServico abrir(int veiculoId, String descricao, BigDecimal valor) throws SQLException {
        veiculoRepository.findById(veiculoId).orElseThrow(() ->
                new IllegalArgumentException("Veículo com id " + veiculoId + " não encontrado. Cadastre o veículo antes de abrir uma OS."));
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da OS não pode ser negativo.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do problema é obrigatória.");
        }
        OrdemServico os = new OrdemServico(veiculoId, descricao, valor, "ABERTA");
        return repository.save(os);
    }

    public void concluir(int osId) throws SQLException {
        OrdemServico os = repository.findById(osId).orElseThrow(() ->
                new IllegalArgumentException("OS com id " + osId + " não encontrada."));
        os.setStatus("CONCLUIDA");
        repository.update(os);
    }

    public Optional<OrdemServico> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<OrdemServico> listarPorVeiculo(int veiculoId) throws SQLException {
        return repository.findByVeiculoId(veiculoId);
    }

    public List<OrdemServico> listarTodas() throws SQLException {
        return repository.findAll();
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
