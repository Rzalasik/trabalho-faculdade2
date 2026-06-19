package com.clinica.repository;

import com.clinica.model.Tutor;
import com.clinica.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TutorRepository {

    public Tutor save(Tutor tutor) throws SQLException {
        String sql = "INSERT INTO tutor (nome, endereco, telefone) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tutor.getNome());
            ps.setString(2, tutor.getEndereco());
            ps.setString(3, tutor.getTelefone());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tutor.setId(rs.getInt(1));
                }
            }
        }
        return tutor;
    }

    public Optional<Tutor> findById(int id) throws SQLException {
        String sql = "SELECT id, nome, endereco, telefone FROM tutor WHERE id = ?";
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

    public List<Tutor> findAll() throws SQLException {
        String sql = "SELECT id, nome, endereco, telefone FROM tutor";
        List<Tutor> tutores = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tutores.add(mapRow(rs));
            }
        }
        return tutores;
    }

    public void update(Tutor tutor) throws SQLException {
        String sql = "UPDATE tutor SET nome = ?, endereco = ?, telefone = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tutor.getNome());
            ps.setString(2, tutor.getEndereco());
            ps.setString(3, tutor.getTelefone());
            ps.setInt(4, tutor.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tutor WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Tutor mapRow(ResultSet rs) throws SQLException {
        Tutor tutor = new Tutor();
        tutor.setId(rs.getInt("id"));
        tutor.setNome(rs.getString("nome"));
        tutor.setEndereco(rs.getString("endereco"));
        tutor.setTelefone(rs.getString("telefone"));
        return tutor;
    }
}
