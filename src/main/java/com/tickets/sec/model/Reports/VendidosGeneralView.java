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
@Table(name = "vendidos_general_view")
public class VendidosGeneralView {

    @Id
    @Column(name = "localidad", length = Integer.MAX_VALUE)
    private String localidad;

    @Column(name = "vendidos")
    private Integer vendidos;

    @Column(name = "disponibles")
    private Integer disponibles;

}