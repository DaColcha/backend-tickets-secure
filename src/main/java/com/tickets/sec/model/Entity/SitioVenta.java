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
@Table(name = "sitio_venta")
public class SitioVenta {
    @Id
    @ColumnDefault("nextval('sitio_venta_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "ubicacion", length = 200)
    private String ubicacion;

    @OneToMany(mappedBy = "sitioVenta")
    private Set<CredencialesSitio> credencialesSitios = new LinkedHashSet<>();

}