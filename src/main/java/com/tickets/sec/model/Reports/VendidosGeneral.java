package com.tickets.sec.model.Reports;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendidos_general_view") // Assuming the view uses this table name
public class VendidosGeneral {

    @Id
    private String zona; // Add fields corresponding to the columns in your view
    private Long vendidos;
    private Long disponibles;

    // Getters and setters for all fields
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
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
}