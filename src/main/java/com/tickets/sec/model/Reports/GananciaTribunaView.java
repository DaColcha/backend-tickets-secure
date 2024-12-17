package com.tickets.sec.model.Reports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "ganancia_tribuna_view")
public class GananciaTribunaView {

    @Id
    @Column(name = "tipo", length = 10)
    private String tipo;

    @Column(name = "total_vendidos")
    private BigDecimal totalVendidos;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "total_ganancia")
    private BigDecimal totalGanancia;

}