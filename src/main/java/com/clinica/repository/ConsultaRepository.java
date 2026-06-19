package com.clinica.repository;

import com.clinica.model.Consulta;
import com.clinica.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultaRepository {

    public Consulta save(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consulta (animal_id, data, motivo, valor) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, consulta.getAnimalId());
            ps.setDate(2, Date.valueOf(consulta.getData()));
            ps.setString(3, consulta.getMotivo());
            ps.setBigDecimal(4, consulta.getValor());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    consulta.setId(rs.getInt(1));
                }
            }
        }
        return consulta;
    }

    public Optional<Consulta> findById(int id) throws SQLException {
        String sql = "SELECT id, animal_id, data, motivo, valor FROM consulta WHERE id = ?";
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

    public List<Consulta> findAll() throws SQLException {
        String sql = "SELECT id, animal_id, data, motivo, valor FROM consulta";
        List<Consulta> consultas = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                consultas.add(mapRow(rs));
            }
        }
        return consultas;
    }

    public List<Consulta> findByAnimalId(int animalId) throws SQLException {
        String sql = "SELECT id, animal_id, data, motivo, valor FROM consulta WHERE animal_id = ?";
        List<Consulta> consultas = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, animalId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    consultas.add(mapRow(rs));
                }
            }
        }
        return consultas;
    }

    public void update(Consulta consulta) throws SQLException {
        String sql = "UPDATE consulta SET animal_id = ?, data = ?, motivo = ?, valor = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, consulta.getAnimalId());
            ps.setDate(2, Date.valueOf(consulta.getData()));
            ps.setString(3, consulta.getMotivo());
            ps.setBigDecimal(4, consulta.getValor());
            ps.setInt(5, consulta.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Consulta mapRow(ResultSet rs) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId(rs.getInt("id"));
        consulta.setAnimalId(rs.getInt("animal_id"));
        consulta.setData(rs.getDate("data").toLocalDate());
        consulta.setMotivo(rs.getString("motivo"));
        consulta.setValor(rs.getBigDecimal("valor"));
        return consulta;
    }
}
