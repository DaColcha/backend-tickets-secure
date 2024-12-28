package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tarjeta {
    private String numero;
    private String nombreTitular;
    private String fechaVencimiento;
    private String cvv;

    public Tarjeta() {
    }

    public Tarjeta(String numeroTarjeta, String nombreTitular, String fechaVencimiento, String cvv) {
        this.numero= numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
    }

}
