package com.oficina.controller;

import com.oficina.model.Veiculo;
import com.oficina.service.VeiculoService;

import java.sql.SQLException;
import java.util.List;

public class VeiculoController {
    private final VeiculoService service = new VeiculoService();

    public Veiculo cadastrar(String placa, String modelo, int ano, int clienteId) {
        try {
            Veiculo veiculo = service.cadastrar(placa, modelo, ano, clienteId);
            System.out.println("[Veiculo] Cadastrado com sucesso: " + veiculo);
            return veiculo;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[Veiculo] Erro ao cadastrar: " + e.getMessage());
            return null;
        }
    }

    public void listarTodos() {
        try {
            List<Veiculo> veiculos = service.listarTodos();
            System.out.println("[Veiculo] Total de veículos: " + veiculos.size());
            veiculos.forEach(v -> System.out.println("  " + v));
        } catch (SQLException e) {
            System.out.println("[Veiculo] Erro ao listar: " + e.getMessage());
        }
    }
}
