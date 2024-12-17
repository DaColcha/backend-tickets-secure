package com.tickets.sec.model.Reports;

import jakarta.persistence.*;
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
@IdClass(VendidosCanchaViewId.class)
@Table(name = "vendidos_cancha_view")
public class VendidosCanchaView {

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