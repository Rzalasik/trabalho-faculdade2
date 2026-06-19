package com.escola.repository;

import com.escola.model.Curso;
import com.escola.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoRepository {

    public Curso save(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso (nome, descricao, carga_horaria, vagas_totais, vagas_disponiveis) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getDescricao());
            ps.setInt(3, curso.getCargaHoraria());
            ps.setInt(4, curso.getVagasTotais());
            ps.setInt(5, curso.getVagasDisponiveis());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    curso.setId(rs.getInt(1));
                }
            }
        }
        return curso;
    }

    public Optional<Curso> findById(int id) throws SQLException {
        String sql = "SELECT id, nome, descricao, carga_horaria, vagas_totais, vagas_disponiveis FROM curso WHERE id = ?";
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

    public List<Curso> findAll() throws SQLException {
        String sql = "SELECT id, nome, descricao, carga_horaria, vagas_totais, vagas_disponiveis FROM curso";
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cursos.add(mapRow(rs));
            }
        }
        return cursos;
    }

    public List<Curso> findByAlunoId(int alunoId) throws SQLException {
        String sql = "SELECT c.id, c.nome, c.descricao, c.carga_horaria, c.vagas_totais, c.vagas_disponiveis " +
                     "FROM curso c JOIN matricula m ON c.id = m.curso_id WHERE m.aluno_id = ?";
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alunoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cursos.add(mapRow(rs));
                }
            }
        }
        return cursos;
    }

    public void update(Curso curso) throws SQLException {
        String sql = "UPDATE curso SET nome = ?, descricao = ?, carga_horaria = ?, vagas_totais = ?, vagas_disponiveis = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getDescricao());
            ps.setInt(3, curso.getCargaHoraria());
            ps.setInt(4, curso.getVagasTotais());
            ps.setInt(5, curso.getVagasDisponiveis());
            ps.setInt(6, curso.getId());
            ps.executeUpdate();
        }
    }

    public void decrementarVaga(int cursoId) throws SQLException {
        String sql = "UPDATE curso SET vagas_disponiveis = vagas_disponiveis - 1 WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cursoId);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM curso WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Curso mapRow(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        curso.setId(rs.getInt("id"));
        curso.setNome(rs.getString("nome"));
        curso.setDescricao(rs.getString("descricao"));
        curso.setCargaHoraria(rs.getInt("carga_horaria"));
        curso.setVagasTotais(rs.getInt("vagas_totais"));
        curso.setVagasDisponiveis(rs.getInt("vagas_disponiveis"));
        return curso;
    }
}
