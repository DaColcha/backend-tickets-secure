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
@Table(name = "vendidos_totales_view")
public class VendidosTotalesView {

    @Id
    @Column(name = "localidad", length = Integer.MAX_VALUE)
    private String localidad;

    @Column(name = "total_vendidos")
    private BigDecimal totalVendidos;

}