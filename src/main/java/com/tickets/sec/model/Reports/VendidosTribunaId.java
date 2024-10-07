package com.tickets.sec.model.Reports;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class VendidosTribunaId implements Serializable {

    @Column(name = "ZONAS")
    private String zona;

    @Column(name = "tipo")
    private String tipo;

    // Getters and setters for both fields
    public String getzona() {
        return zona;
    }

    public void setzona(String zona) {
        this.zona = zona;
    }

    public String getTipoTribuna() {
        return tipo;
    }

    public void setTipoTribuna(String tipo) {
        this.tipo = tipo;
    }

    // Equals and hashCode methods for composite primary key
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VendidosTribunaId that = (VendidosTribunaId) o;
        return zona.equals(that.zona) && tipo.equals(that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zona, tipo);
    }
}