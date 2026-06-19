package com.clinica.controller;

import com.clinica.model.Consulta;
import com.clinica.service.ConsultaService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ConsultaController {
    private final ConsultaService service = new ConsultaService();

    public Consulta registrar(int animalId, LocalDate data, String motivo, BigDecimal valor) {
        try {
            Consulta consulta = service.registrar(animalId, data, motivo, valor);
            System.out.println("[Consulta] Registrada com sucesso: " + consulta);
            return consulta;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[Consulta] Erro ao registrar: " + e.getMessage());
            return null;
        }
    }

    public void listarPorAnimal(int animalId) {
        try {
            List<Consulta> consultas = service.listarPorAnimal(animalId);
            System.out.println("[Consulta] Consultas do animal id=" + animalId + ": " + consultas.size());
            consultas.forEach(c -> System.out.println("  " + c));
        } catch (SQLException e) {
            System.out.println("[Consulta] Erro ao listar: " + e.getMessage());
        }
    }

    public void listarTodas() {
        try {
            List<Consulta> consultas = service.listarTodas();
            System.out.println("[Consulta] Total de consultas: " + consultas.size());
            consultas.forEach(c -> System.out.println("  " + c));
        } catch (SQLException e) {
            System.out.println("[Consulta] Erro ao listar: " + e.getMessage());
        }
    }
}
