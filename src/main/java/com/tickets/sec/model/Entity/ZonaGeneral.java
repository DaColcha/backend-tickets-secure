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
@Table(name = "zona_general")
public class ZonaGeneral {
    @Id
    @ColumnDefault("nextval('zona_general_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "localidad", nullable = false, length = Integer.MAX_VALUE)
    private String localidad;

    @Column(name = "disponibles", nullable = false)
    private Integer disponibles;

    @OneToMany(mappedBy = "zonaGeneral")
    private Set<VentasZonaGeneral> ventasZonaGenerals = new LinkedHashSet<>();

    public ZonaGeneral(String localidad, Integer disponibles) {
        this.localidad = localidad;
        this.disponibles = disponibles;
    }

    public ZonaGeneral() {

    }
}