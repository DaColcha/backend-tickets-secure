package com.tickets.sec.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zona_general")
public class ZonaGeneral {

    @Id
    private String zona;

    @Column(name = "boletos")
    private Integer boletos;

    public ZonaGeneral() {
    }

    public ZonaGeneral(String string, int i) {
        this.zona = string;
        this.boletos = i;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Integer getBoletos() {
        return boletos;
    }

    public void setBoletos(int boletos) {
        this.boletos = boletos;
    }

}
