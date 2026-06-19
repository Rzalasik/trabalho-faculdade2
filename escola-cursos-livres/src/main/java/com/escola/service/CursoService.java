package com.escola.service;

import com.escola.model.Curso;
import com.escola.repository.CursoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CursoService {
    private final CursoRepository repository = new CursoRepository();

    public Curso cadastrar(String nome, String descricao, int cargaHoraria, int vagasTotais) throws SQLException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do curso é obrigatório.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser positiva.");
        }
        if (vagasTotais <= 0) {
            throw new IllegalArgumentException("Vagas totais deve ser positivo.");
        }
        Curso curso = new Curso(nome, descricao, cargaHoraria, vagasTotais, vagasTotais);
        return repository.save(curso);
    }

    public Optional<Curso> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Curso> listarTodos() throws SQLException {
        return repository.findAll();
    }

    public List<Curso> listarPorAluno(int alunoId) throws SQLException {
        return repository.findByAlunoId(alunoId);
    }

    public void atualizar(Curso curso) throws SQLException {
        repository.update(curso);
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
