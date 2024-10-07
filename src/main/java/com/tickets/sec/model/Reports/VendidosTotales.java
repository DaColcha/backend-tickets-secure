package com.tickets.sec.model.Reports;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendidos_totales_view")
public class VendidosTotales {

    @Id
    private String localidad;

    private Long totalVendidos;

    public VendidosTotales() {
    }

    public String getLocalidad() {
        return localidad;
    }

    public Long getTotalVendidos() {
        return totalVendidos;
    }

}
