package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ventas_id_seq")
    @SequenceGenerator(name = "ventas_id_seq", sequenceName = "ventas_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_numerada")
    private VentasAsientosNumerado ventaNumerada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_zona_general")
    private VentasZonaGeneral ventaZonaGeneral;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pago")
    private Pago pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor")
    private CredencialesSitio vendedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abonado")
    private Abonado abonado;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;

    @Column(name = "total_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalVenta;

    @Column(name = "tipo_venta", nullable = false)
    private String tipoVenta;

    public Venta() {
    }

    public Venta(VentasAsientosNumerado ventaNumerada, VentasZonaGeneral ventaZonaGeneral, Pago pago, CredencialesSitio vendedor, Abonado abonado, LocalDate fechaVenta, BigDecimal totalVenta) {
        this.ventaNumerada = ventaNumerada;
        this.ventaZonaGeneral = ventaZonaGeneral;
        this.pago = pago;
        this.vendedor = vendedor;
        this.abonado = abonado;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
    }
}