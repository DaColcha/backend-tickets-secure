package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "credenciales_sitio")
public class CredencialesSitio {
    @Id
    @ColumnDefault("nextval('credenciales_sitio_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credencial")
    private Credenciales credencial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sitio_venta")
    private SitioVenta sitioVenta;

    @Column(name = "valido_hasta", nullable = false)
    private LocalDate validoHasta;

    @OneToMany(mappedBy = "vendedor")
    private Set<Venta> ventas = new LinkedHashSet<>();

}