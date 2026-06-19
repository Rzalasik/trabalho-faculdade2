package com.oficina.repository;

import com.oficina.model.OrdemServico;
import com.oficina.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdemServicoRepository {

    public OrdemServico save(OrdemServico os) throws SQLException {
        String sql = "INSERT INTO ordem_servico (veiculo_id, descricao_problema, valor, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, os.getVeiculoId());
            ps.setString(2, os.getDescricaoProblema());
            ps.setBigDecimal(3, os.getValor());
            ps.setString(4, os.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    os.setId(rs.getInt(1));
                }
            }
        }
        return os;
    }

    public Optional<OrdemServico> findById(int id) throws SQLException {
        String sql = "SELECT id, veiculo_id, descricao_problema, valor, status FROM ordem_servico WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    public List<OrdemServico> findAll() throws SQLException {
        String sql = "SELECT id, veiculo_id, descricao_problema, valor, status FROM ordem_servico";
        List<OrdemServico> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    public List<OrdemServico> findByVeiculoId(int veiculoId) throws SQLException {
        String sql = "SELECT id, veiculo_id, descricao_problema, valor, status FROM ordem_servico WHERE veiculo_id = ?";
        List<OrdemServico> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, veiculoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    public void update(OrdemServico os) throws SQLException {
        String sql = "UPDATE ordem_servico SET veiculo_id = ?, descricao_problema = ?, valor = ?, status = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, os.getVeiculoId());
            ps.setString(2, os.getDescricaoProblema());
            ps.setBigDecimal(3, os.getValor());
            ps.setString(4, os.getStatus());
            ps.setInt(5, os.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM ordem_servico WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private OrdemServico mapRow(ResultSet rs) throws SQLException {
        OrdemServico os = new OrdemServico();
        os.setId(rs.getInt("id"));
        os.setVeiculoId(rs.getInt("veiculo_id"));
        os.setDescricaoProblema(rs.getString("descricao_problema"));
        os.setValor(rs.getBigDecimal("valor"));
        os.setStatus(rs.getString("status"));
        return os;
    }
}
