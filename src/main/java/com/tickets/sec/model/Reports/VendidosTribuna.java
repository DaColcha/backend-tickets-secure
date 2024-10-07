package com.tickets.sec.model.Reports;

import jakarta.persistence.*;

@Entity
@Table(name = "vendidos_tribuna_view")
@IdClass(VendidosTribunaId.class)
public class VendidosTribuna {

    @Id
    @Column(name = "ZONAS")
    private String zona;

    @Id
    @Column(name = "tipo")
    private String tipo;

    private Long vendidos;
    private Long disponibles;

    public String getZona() {
        return zona;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getVendidos() {
        return vendidos;
    }

    public Long getDisponibles() {
        return disponibles;
    }

}
