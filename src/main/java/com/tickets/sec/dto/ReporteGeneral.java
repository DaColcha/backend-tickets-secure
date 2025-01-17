package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteGeneral {

    private Integer disponibles;
    private Integer vendidos;

    public ReporteGeneral(Integer disponibles, Integer vendidos) {
        this.disponibles = disponibles;
        this.vendidos = vendidos;
    }
}
