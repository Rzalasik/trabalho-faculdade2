package com.escola.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Matricula {
    private int id;
    private int alunoId;
    private int cursoId;
    private LocalDate data;
    private BigDecimal valorPago;

    public Matricula() {}

    public Matricula(int alunoId, int cursoId, LocalDate data, BigDecimal valorPago) {
        this.alunoId = alunoId;
        this.cursoId = cursoId;
        this.data = data;
        this.valorPago = valorPago;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }

    @Override
    public String toString() {
        return "Matricula{id=" + id + ", alunoId=" + alunoId + ", cursoId=" + cursoId + ", data=" + data + ", valorPago=" + valorPago + "}";
    }
}
