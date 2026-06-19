package com.escola.service;

import com.escola.model.Aluno;
import com.escola.repository.AlunoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AlunoService {
    private final AlunoRepository repository = new AlunoRepository();

    public Aluno cadastrar(String nome, String email, String telefone) throws SQLException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do aluno é obrigatório.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail do aluno é obrigatório.");
        }
        Aluno aluno = new Aluno(nome, email, telefone);
        return repository.save(aluno);
    }

    public Optional<Aluno> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Aluno> listarTodos() throws SQLException {
        return repository.findAll();
    }

    public List<Aluno> listarPorCurso(int cursoId) throws SQLException {
        return repository.findByCursoId(cursoId);
    }

    public void atualizar(Aluno aluno) throws SQLException {
        repository.update(aluno);
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
