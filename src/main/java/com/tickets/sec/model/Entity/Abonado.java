package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "abonados")
public class Abonado {
    @Id
    @Column(name = "cedula", nullable = false, length = 10)
    private String cedula;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @OneToMany(mappedBy = "abonado", fetch = FetchType.LAZY)
    private Set<Venta> ventas = new LinkedHashSet<>();

}