package com.oficina.model;

import java.math.BigDecimal;

public class OrdemServico {
    private int id;
    private int veiculoId;
    private String descricaoProblema;
    private BigDecimal valor;
    private String status;

    public OrdemServico() {}

    public OrdemServico(int veiculoId, String descricaoProblema, BigDecimal valor, String status) {
        this.veiculoId = veiculoId;
        this.descricaoProblema = descricaoProblema;
        this.valor = valor;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVeiculoId() { return veiculoId; }
    public void setVeiculoId(int veiculoId) { this.veiculoId = veiculoId; }

    public String getDescricaoProblema() { return descricaoProblema; }
    public void setDescricaoProblema(String descricaoProblema) { this.descricaoProblema = descricaoProblema; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "OrdemServico{id=" + id + ", veiculoId=" + veiculoId + ", descricao='" + descricaoProblema + "', valor=" + valor + ", status='" + status + "'}";
    }
}
