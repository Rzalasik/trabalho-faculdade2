package com.clinica.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Consulta {
    private int id;
    private int animalId;
    private LocalDate data;
    private String motivo;
    private BigDecimal valor;

    public Consulta() {}

    public Consulta(int animalId, LocalDate data, String motivo, BigDecimal valor) {
        this.animalId = animalId;
        this.data = data;
        this.motivo = motivo;
        this.valor = valor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAnimalId() { return animalId; }
    public void setAnimalId(int animalId) { this.animalId = animalId; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    @Override
    public String toString() {
        return "Consulta{id=" + id + ", animalId=" + animalId + ", data=" + data + ", motivo='" + motivo + "', valor=" + valor + "}";
    }
}
