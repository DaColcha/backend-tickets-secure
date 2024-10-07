package com.tickets.sec.model;

public class ReporteGeneral {

    private Integer disponibles;
    private Integer vendidos;

    public ReporteGeneral(Integer disponibles, Integer vendidos) {
        this.disponibles = disponibles;
        this.vendidos = vendidos;
    }

    public Integer getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }

}
