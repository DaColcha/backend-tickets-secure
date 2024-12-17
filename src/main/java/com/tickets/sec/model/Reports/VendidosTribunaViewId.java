package com.tickets.sec.model.Reports;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class VendidosTribunaViewId implements Serializable {
    @Column(name = "zonas", length = Integer.MAX_VALUE)
    private String zonas;

    @Column(name = "tipo", length = 10)
    private String tipo;


    public String getZonas() {
        return zonas;
    }

    public void setZonas(String zonas) {
        this.zonas = zonas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VendidosTribunaViewId that = (VendidosTribunaViewId) o;
        return zonas.equals(that.zonas) && tipo.equals(that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zonas, tipo);
    }
}
