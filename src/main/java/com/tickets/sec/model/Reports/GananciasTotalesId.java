package com.tickets.sec.model.Reports;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;

public class GananciasTotalesId implements Serializable {

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "tipo")
    private String tipo;

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
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
        GananciasTotalesId that = (GananciasTotalesId) o;
        return localidad.equals(that.localidad) && tipo.equals(that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localidad, tipo);
    }
}