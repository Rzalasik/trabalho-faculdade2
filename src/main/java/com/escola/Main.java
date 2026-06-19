package com.escola;

import com.escola.controller.AlunoController;
import com.escola.controller.CursoController;
import com.escola.controller.MatriculaController;
import com.escola.model.Aluno;
import com.escola.model.Curso;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();
        CursoController cursoController = new CursoController();
        MatriculaController matriculaController = new MatriculaController();

        System.out.println("=== ESCOLA DE CURSOS LIVRES ===\n");

        // 1. Criar aluno principal
        System.out.println("--- Cadastrando alunos ---");
        Aluno aluno1 = alunoController.cadastrar("Ana Lima", "ana@email.com", "11977770000");
        Aluno aluno2 = alunoController.cadastrar("Bruno Costa", "bruno@email.com", "11966660000");

        if (aluno1 == null || aluno2 == null) {
            System.out.println("Não foi possível continuar sem alunos cadastrados.");
            return;
        }

        // 2. Criar curso com apenas 1 vaga disponível
        System.out.println("\n--- Cadastrando curso (1 vaga) ---");
        Curso curso = cursoController.cadastrar("Java para Iniciantes", "Fundamentos da linguagem Java", 40, 1);

        if (curso == null) {
            System.out.println("Não foi possível continuar sem curso cadastrado.");
            return;
        }

        // 3. Matricular aluno1 com sucesso (vagas: 1 → 0)
        System.out.println("\n--- Matriculando aluno1 (deve ter sucesso, vagas: 1→0) ---");
        matriculaController.matricular(aluno1.getId(), curso.getId(), LocalDate.now(), new BigDecimal("299.90"));

        // 4. Tentar matricular aluno1 novamente no mesmo curso (deve falhar: duplicado)
        System.out.println("\n--- Tentativa de matrícula duplicada do aluno1 (deve falhar) ---");
        matriculaController.matricular(aluno1.getId(), curso.getId(), LocalDate.now(), new BigDecimal("299.90"));

        // 5. Tentar matricular aluno2 (curso sem vagas — deve falhar)
        System.out.println("\n--- Tentativa de matrícula do aluno2 (curso sem vagas — deve falhar) ---");
        matriculaController.matricular(aluno2.getId(), curso.getId(), LocalDate.now(), new BigDecimal("299.90"));

        // 6. Listar alunos do curso
        System.out.println("\n--- Alunos do curso ---");
        cursoController.listarAlunosDoCurso(curso.getId());

        // 7. Listar cursos do aluno1
        System.out.println("\n--- Cursos do aluno1 ---");
        alunoController.listarCursosDoAluno(aluno1.getId());

        System.out.println("\n=== FIM DO FLUXO ===");
    }
}
