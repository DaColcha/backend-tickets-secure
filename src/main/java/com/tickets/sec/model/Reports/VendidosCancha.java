package com.tickets.sec.model.Reports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendidos_cancha_view")
public class VendidosCancha {

    @Id
    @Column(name = "ZONAS")
    private String zona;

    private String tipo;

    private Long vendidos;

    private Long disponibles;

    public String getzona() {
        return zona;
    }

    public void setzona(String zona) {
        this.zona = zona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getVendidos() {
        return vendidos;
    }

    public void setVendidos(Long vendidos) {
        this.vendidos = vendidos;
    }

    public Long getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(Long disponibles) {
        this.disponibles = disponibles;
    }

    @Override
    public String toString() {
        return "VendidosCancha{" +
                "zona='" + zona
                + '\'' +
                ", tipo='" + tipo + '\'' +
                ", vendidos=" + vendidos +
                ", disponibles=" + disponibles +
                '}';
    }
}
