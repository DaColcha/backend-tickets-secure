package com.tickets.sec.model.Reports;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@IdClass(GananciasTotalesViewId.class)
@Table(name = "ganancias_totales_view")
public class GananciasTotalesView {

    @Id
    @Column(name = "localidad", length = Integer.MAX_VALUE)
    private String localidad;

    @Id
    @Column(name = "tipo", length = Integer.MAX_VALUE)
    private String tipo;

    @Column(name = "total_vendidos")
    private BigDecimal totalVendidos;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "total_ganancia")
    private BigDecimal totalGanancia;

}