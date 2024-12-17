package com.tickets.sec.model.Entity;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class AsientosNumeradoId implements Serializable {

    @Column(name = "localidad", nullable = false, length = Integer.MAX_VALUE)
    private String localidad;
    @Column(name = "zona", nullable = false, length = Integer.MAX_VALUE)
    private String zona;
    @Column(name = "tipo", nullable = false, length = Integer.MAX_VALUE)
    private String tipo;
    @Column(name = "numero", nullable = false, length = Integer.MAX_VALUE)
    private Integer numero;

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AsientosNumeradoId)) return false;
        AsientosNumeradoId that = (AsientosNumeradoId) o;
        return getLocalidad().equals(that.getLocalidad()) && getZona().equals(that.getZona()) && getTipo().equals(that.getTipo()) && getNumero().equals(that.getNumero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocalidad(), getZona(), getTipo(), getNumero());
    }
}
