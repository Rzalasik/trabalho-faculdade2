package com.escola.controller;

import com.escola.model.Aluno;
import com.escola.model.Curso;
import com.escola.service.AlunoService;
import com.escola.service.CursoService;

import java.sql.SQLException;
import java.util.List;

public class AlunoController {
    private final AlunoService service = new AlunoService();
    private final CursoService cursoService = new CursoService();

    public Aluno cadastrar(String nome, String email, String telefone) {
        try {
            Aluno aluno = service.cadastrar(nome, email, telefone);
            System.out.println("[Aluno] Cadastrado com sucesso: " + aluno);
            return aluno;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[Aluno] Erro ao cadastrar: " + e.getMessage());
            return null;
        }
    }

    public void listarTodos() {
        try {
            List<Aluno> alunos = service.listarTodos();
            System.out.println("[Aluno] Total de alunos: " + alunos.size());
            alunos.forEach(a -> System.out.println("  " + a));
        } catch (SQLException e) {
            System.out.println("[Aluno] Erro ao listar: " + e.getMessage());
        }
    }

    public void listarCursosDoAluno(int alunoId) {
        try {
            List<Curso> cursos = cursoService.listarPorAluno(alunoId);
            System.out.println("[Aluno] Cursos do aluno id=" + alunoId + ": " + cursos.size());
            cursos.forEach(c -> System.out.println("  " + c));
        } catch (SQLException e) {
            System.out.println("[Aluno] Erro ao listar cursos: " + e.getMessage());
        }
    }
}
