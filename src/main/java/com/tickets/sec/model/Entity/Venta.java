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
    @ColumnDefault("nextval('ventas_id_seq')")
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

}