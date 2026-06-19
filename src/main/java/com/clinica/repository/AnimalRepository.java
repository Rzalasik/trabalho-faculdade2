package com.clinica.repository;

import com.clinica.model.Animal;
import com.clinica.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalRepository {

    public Animal save(Animal animal) throws SQLException {
        String sql = "INSERT INTO animal (nome, especie, raca, tutor_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, animal.getNome());
            ps.setString(2, animal.getEspecie());
            ps.setString(3, animal.getRaca());
            ps.setInt(4, animal.getTutorId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    animal.setId(rs.getInt(1));
                }
            }
        }
        return animal;
    }

    public Optional<Animal> findById(int id) throws SQLException {
        String sql = "SELECT id, nome, especie, raca, tutor_id FROM animal WHERE id = ?";
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

    public List<Animal> findAll() throws SQLException {
        String sql = "SELECT id, nome, especie, raca, tutor_id FROM animal";
        List<Animal> animais = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                animais.add(mapRow(rs));
            }
        }
        return animais;
    }

    public List<Animal> findByTutorId(int tutorId) throws SQLException {
        String sql = "SELECT id, nome, especie, raca, tutor_id FROM animal WHERE tutor_id = ?";
        List<Animal> animais = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tutorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    animais.add(mapRow(rs));
                }
            }
        }
        return animais;
    }

    public void update(Animal animal) throws SQLException {
        String sql = "UPDATE animal SET nome = ?, especie = ?, raca = ?, tutor_id = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, animal.getNome());
            ps.setString(2, animal.getEspecie());
            ps.setString(3, animal.getRaca());
            ps.setInt(4, animal.getTutorId());
            ps.setInt(5, animal.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM animal WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Animal mapRow(ResultSet rs) throws SQLException {
        Animal animal = new Animal();
        animal.setId(rs.getInt("id"));
        animal.setNome(rs.getString("nome"));
        animal.setEspecie(rs.getString("especie"));
        animal.setRaca(rs.getString("raca"));
        animal.setTutorId(rs.getInt("tutor_id"));
        return animal;
    }
}
