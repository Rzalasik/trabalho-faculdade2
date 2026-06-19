package com.escola.controller;

import com.escola.model.Matricula;
import com.escola.service.MatriculaService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MatriculaController {
    private final MatriculaService service = new MatriculaService();

    public Matricula matricular(int alunoId, int cursoId, LocalDate data, BigDecimal valorPago) {
        try {
            Matricula matricula = service.matricular(alunoId, cursoId, data, valorPago);
            System.out.println("[Matricula] Realizada com sucesso: " + matricula);
            return matricula;
        } catch (IllegalArgumentException | IllegalStateException | SQLException e) {
            System.out.println("[Matricula] Erro: " + e.getMessage());
            return null;
        }
    }

    public void listarTodas() {
        try {
            List<Matricula> matriculas = service.listarTodas();
            System.out.println("[Matricula] Total de matrículas: " + matriculas.size());
            matriculas.forEach(m -> System.out.println("  " + m));
        } catch (SQLException e) {
            System.out.println("[Matricula] Erro ao listar: " + e.getMessage());
        }
    }
}
