package com.clinica.service;

import com.clinica.model.Tutor;
import com.clinica.repository.TutorRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TutorService {
    private final TutorRepository repository = new TutorRepository();

    public Tutor cadastrar(String nome, String endereco, String telefone) throws SQLException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do tutor é obrigatório.");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone do tutor é obrigatório.");
        }
        Tutor tutor = new Tutor(nome, endereco, telefone);
        return repository.save(tutor);
    }

    public Optional<Tutor> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Tutor> listarTodos() throws SQLException {
        return repository.findAll();
    }

    public void atualizar(Tutor tutor) throws SQLException {
        if (tutor.getNome() == null || tutor.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do tutor é obrigatório.");
        }
        repository.update(tutor);
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
