package com.escola.service;

import com.escola.model.Aluno;
import com.escola.model.Curso;
import com.escola.model.Matricula;
import com.escola.repository.AlunoRepository;
import com.escola.repository.CursoRepository;
import com.escola.repository.MatriculaRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MatriculaService {
    private final MatriculaRepository repository = new MatriculaRepository();
    private final AlunoRepository alunoRepository = new AlunoRepository();
    private final CursoRepository cursoRepository = new CursoRepository();

    public Matricula matricular(int alunoId, int cursoId, LocalDate data, BigDecimal valorPago) throws SQLException {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() ->
                new IllegalArgumentException("Aluno com id " + alunoId + " não encontrado."));

        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() ->
                new IllegalArgumentException("Curso com id " + cursoId + " não encontrado."));

        if (valorPago == null || valorPago.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor pago não pode ser negativo.");
        }

        Optional<Matricula> existente = repository.findByAlunoECurso(alunoId, cursoId);
        if (existente.isPresent()) {
            throw new IllegalStateException("Aluno '" + aluno.getNome() + "' já está matriculado no curso '" + curso.getNome() + "'.");
        }

        if (curso.getVagasDisponiveis() <= 0) {
            throw new IllegalStateException("Curso '" + curso.getNome() + "' não possui vagas disponíveis.");
        }

        Matricula matricula = new Matricula(alunoId, cursoId, data, valorPago);
        repository.save(matricula);
        cursoRepository.decrementarVaga(cursoId);
        return matricula;
    }

    public Optional<Matricula> buscarPorId(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Matricula> listarTodas() throws SQLException {
        return repository.findAll();
    }

    public void excluir(int id) throws SQLException {
        repository.delete(id);
    }
}
