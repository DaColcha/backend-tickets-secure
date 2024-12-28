package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ventas_zona_general")
public class VentasZonaGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ventas_zona_general_seq")
    @SequenceGenerator(name = "ventas_zona_general_seq", sequenceName = "ventas_zona_general_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zona_general")
    private ZonaGeneral zonaGeneral;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @OneToMany(mappedBy = "ventaZonaGeneral")
    private Set<Venta> ventas = new LinkedHashSet<>();

    public VentasZonaGeneral(ZonaGeneral zonaGeneral, Integer cantidad) {
        this.zonaGeneral = zonaGeneral;
        this.cantidad = cantidad;
    }

    public VentasZonaGeneral() {

    }
}