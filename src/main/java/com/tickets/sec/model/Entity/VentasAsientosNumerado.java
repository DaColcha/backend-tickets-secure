package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ventas_asientos_numerados")
public class VentasAsientosNumerado {
    @Id
    @ColumnDefault("nextval('ventas_asientos_numerados_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ElementCollection
    @Column(name = "asientos", nullable = false)
    private List<Integer> asientos;

    @OneToMany(mappedBy = "ventaNumerada")
    private Set<Venta> ventas = new LinkedHashSet<>();

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    public VentasAsientosNumerado() {
    }

    public VentasAsientosNumerado(List<Integer> asientos, Integer cantidad) {
        this.asientos = asientos;
        this.cantidad = cantidad;
    }
}