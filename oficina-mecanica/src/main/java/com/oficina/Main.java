package com.oficina;

import com.oficina.controller.ClienteController;
import com.oficina.controller.OrdemServicoController;
import com.oficina.controller.VeiculoController;
import com.oficina.model.Cliente;
import com.oficina.model.OrdemServico;
import com.oficina.model.Veiculo;

import com.oficina.util.Conexao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static void limparBanco() throws SQLException {
        try (Connection c = Conexao.getConexao(); Statement s = c.createStatement()) {
            s.execute("TRUNCATE ordem_servico, veiculo, cliente RESTART IDENTITY CASCADE");
        }
    }

    public static void main(String[] args) {
        try { limparBanco(); } catch (SQLException e) { System.out.println("Aviso: não foi possível limpar o banco: " + e.getMessage()); }
        ClienteController clienteController = new ClienteController();
        VeiculoController veiculoController = new VeiculoController();
        OrdemServicoController osController = new OrdemServicoController();

        System.out.println("=== OFICINA MECÂNICA ===\n");

        // 1. Criar cliente
        System.out.println("--- Cadastrando cliente ---");
        Cliente cliente = clienteController.cadastrar("Maria Oliveira", "11988880000");

        if (cliente == null) {
            System.out.println("Não foi possível continuar sem um cliente cadastrado.");
            return;
        }

        // 2. Criar veículo vinculado ao cliente
        System.out.println("\n--- Cadastrando veículo ---");
        Veiculo veiculo = veiculoController.cadastrar("ABC-1234", "Honda Civic", 2020, cliente.getId());

        if (veiculo == null) {
            System.out.println("Não foi possível continuar sem um veículo cadastrado.");
            return;
        }

        // 3. Abrir ordem de serviço
        System.out.println("\n--- Abrindo OS ---");
        OrdemServico os = osController.abrir(veiculo.getId(), "Troca de óleo e filtro", new BigDecimal("250.00"));

        if (os == null) {
            System.out.println("Não foi possível continuar sem uma OS aberta.");
            return;
        }

        // 4. Listar OSs do veículo
        System.out.println("\n--- Histórico de OSs do veículo ---");
        osController.listarPorVeiculo(veiculo.getId());

        // 5. Concluir a OS
        System.out.println("\n--- Concluindo OS ---");
        osController.concluir(os.getId());

        // 6. Listar OSs novamente para ver status atualizado
        System.out.println("\n--- Histórico atualizado ---");
        osController.listarPorVeiculo(veiculo.getId());

        // 7. Tentar concluir OS já concluída (deve falhar)
        System.out.println("\n--- Tentativa de concluir OS já concluída (deve falhar) ---");
        osController.concluir(os.getId());

        // 8. Tentar abrir OS com valor negativo (deve falhar)
        System.out.println("\n--- Tentativa de OS com valor negativo (deve falhar) ---");
        osController.abrir(veiculo.getId(), "Serviço inválido", new BigDecimal("-100.00"));

        System.out.println("\n=== FIM DO FLUXO ===");
    }
}
