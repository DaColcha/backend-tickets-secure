package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un reporte general de boletos disponibles y vendidos.
 */
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
