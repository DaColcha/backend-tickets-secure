package com.tickets.sec.model.Reports;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "ganancias_totales_view")
@IdClass(GananciasTotalesId.class)
public class GananciasTotales {

    @Id
    private String localidad;

    @Id
    private String tipo;

    private Long totalVendidos;
    private Long precio;
    private Long totalGanancia;

    public GananciasTotales() {
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getTotalVendidos() {
        return totalVendidos;
    }

    public Long getPrecio() {
        return precio;
    }

    public Long getTotalGanancia() {
        return totalGanancia;
    }

}
