package com.oficina.controller;

import com.oficina.model.OrdemServico;
import com.oficina.service.OrdemServicoService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrdemServicoController {
    private final OrdemServicoService service = new OrdemServicoService();

    public OrdemServico abrir(int veiculoId, String descricao, BigDecimal valor) {
        try {
            OrdemServico os = service.abrir(veiculoId, descricao, valor);
            System.out.println("[OS] Aberta com sucesso: " + os);
            return os;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[OS] Erro ao abrir: " + e.getMessage());
            return null;
        }
    }

    public void concluir(int osId) {
        try {
            service.concluir(osId);
            System.out.println("[OS] OS id=" + osId + " marcada como CONCLUIDA.");
        } catch (IllegalArgumentException | IllegalStateException | SQLException e) {
            System.out.println("[OS] Erro ao concluir: " + e.getMessage());
        }
    }

    public void listarPorVeiculo(int veiculoId) {
        try {
            List<OrdemServico> lista = service.listarPorVeiculo(veiculoId);
            System.out.println("[OS] Histórico do veículo id=" + veiculoId + ": " + lista.size() + " registro(s)");
            lista.forEach(os -> System.out.println("  " + os));
        } catch (SQLException e) {
            System.out.println("[OS] Erro ao listar: " + e.getMessage());
        }
    }

    public void listarTodas() {
        try {
            List<OrdemServico> lista = service.listarTodas();
            System.out.println("[OS] Total de ordens de serviço: " + lista.size());
            lista.forEach(os -> System.out.println("  " + os));
        } catch (SQLException e) {
            System.out.println("[OS] Erro ao listar: " + e.getMessage());
        }
    }
}
