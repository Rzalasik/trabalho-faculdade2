package com.clinica.service;

import com.clinica.model.Consulta;
import com.clinica.repository.AnimalRepository;
import com.clinica.repository.ConsultaRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ConsultaService {
    private final ConsultaRepository repository = new ConsultaRepository();
    private final AnimalRepository animalRepository = new AnimalRepository();

    public Consulta registrar(int animalId, LocalDate data, String motivo, BigDecimal valor) throws SQLException {
        animalRepository.findById(animalId).orElseThrow(() ->
                new IllegalArgumentException("Animal com id " + animalId + " não encontrado. Cadastre o animal antes de registrar uma consulta."));
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("O motivo da consulta é obrigatório.");
        }
        Consulta consulta = new Consulta(animalId, data, motivo, valor);
        return repository.save(consulta);
    }

    public Optional<Consulta> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Consulta> listarPorAnimal(int animalId) throws SQLException {
        return repository.findByAnimalId(animalId);
    }

    public List<Consulta> listarTodas() throws SQLException {
        return repository.findAll();
    }

    public void atualizar(Consulta consulta) throws SQLException {
        if (consulta.getValor() != null && consulta.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");
        }
        repository.update(consulta);
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
