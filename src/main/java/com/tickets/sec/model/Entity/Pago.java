package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formapago")
    private FormaPago formapago;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @Column(name = "comprobante", length = 50)
    private String comprobante;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "plazo_meses")
    private Integer plazoMeses;

    @OneToMany(mappedBy = "pago")
    private Set<Venta> ventas = new LinkedHashSet<>();

}