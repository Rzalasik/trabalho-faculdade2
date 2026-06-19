package com.escola.repository;

import com.escola.model.Matricula;
import com.escola.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatriculaRepository {

    public Matricula save(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO matricula (aluno_id, curso_id, data, valor_pago) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, matricula.getAlunoId());
            ps.setInt(2, matricula.getCursoId());
            ps.setDate(3, Date.valueOf(matricula.getData()));
            ps.setBigDecimal(4, matricula.getValorPago());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    matricula.setId(rs.getInt(1));
                }
            }
        }
        return matricula;
    }

    public Optional<Matricula> findById(int id) throws SQLException {
        String sql = "SELECT id, aluno_id, curso_id, data, valor_pago FROM matricula WHERE id = ?";
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

    public Optional<Matricula> findByAlunoECurso(int alunoId, int cursoId) throws SQLException {
        String sql = "SELECT id, aluno_id, curso_id, data, valor_pago FROM matricula WHERE aluno_id = ? AND curso_id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alunoId);
            ps.setInt(2, cursoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    public List<Matricula> findAll() throws SQLException {
        String sql = "SELECT id, aluno_id, curso_id, data, valor_pago FROM matricula";
        List<Matricula> matriculas = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                matriculas.add(mapRow(rs));
            }
        }
        return matriculas;
    }

    public void update(Matricula matricula) throws SQLException {
        String sql = "UPDATE matricula SET aluno_id = ?, curso_id = ?, data = ?, valor_pago = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, matricula.getAlunoId());
            ps.setInt(2, matricula.getCursoId());
            ps.setDate(3, Date.valueOf(matricula.getData()));
            ps.setBigDecimal(4, matricula.getValorPago());
            ps.setInt(5, matricula.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM matricula WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Matricula mapRow(ResultSet rs) throws SQLException {
        Matricula matricula = new Matricula();
        matricula.setId(rs.getInt("id"));
        matricula.setAlunoId(rs.getInt("aluno_id"));
        matricula.setCursoId(rs.getInt("curso_id"));
        matricula.setData(rs.getDate("data").toLocalDate());
        matricula.setValorPago(rs.getBigDecimal("valor_pago"));
        return matricula;
    }
}
