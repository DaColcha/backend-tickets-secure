package com.tickets.sec.model.Reports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "vendidos_tribuna_view")
public class VendidosTribunaView {

    @Id
    @Column(name = "zonas", length = Integer.MAX_VALUE)
    private String zonas;

    @Id
    @Column(name = "tipo", length = 10)
    private String tipo;

    @Column(name = "vendidos")
    private Long vendidos;

    @Column(name = "disponibles")
    private Long disponibles;

}