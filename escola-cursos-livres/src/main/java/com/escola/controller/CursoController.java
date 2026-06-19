package com.escola.controller;

import com.escola.model.Aluno;
import com.escola.model.Curso;
import com.escola.service.AlunoService;
import com.escola.service.CursoService;

import java.sql.SQLException;
import java.util.List;

public class CursoController {
    private final CursoService service = new CursoService();
    private final AlunoService alunoService = new AlunoService();

    public Curso cadastrar(String nome, String descricao, int cargaHoraria, int vagasTotais) {
        try {
            Curso curso = service.cadastrar(nome, descricao, cargaHoraria, vagasTotais);
            System.out.println("[Curso] Cadastrado com sucesso: " + curso);
            return curso;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[Curso] Erro ao cadastrar: " + e.getMessage());
            return null;
        }
    }

    public void listarTodos() {
        try {
            List<Curso> cursos = service.listarTodos();
            System.out.println("[Curso] Total de cursos: " + cursos.size());
            cursos.forEach(c -> System.out.println("  " + c));
        } catch (SQLException e) {
            System.out.println("[Curso] Erro ao listar: " + e.getMessage());
        }
    }

    public void listarAlunosDoCurso(int cursoId) {
        try {
            List<Aluno> alunos = alunoService.listarPorCurso(cursoId);
            System.out.println("[Curso] Alunos do curso id=" + cursoId + ": " + alunos.size());
            alunos.forEach(a -> System.out.println("  " + a));
        } catch (SQLException e) {
            System.out.println("[Curso] Erro ao listar alunos: " + e.getMessage());
        }
    }
}
