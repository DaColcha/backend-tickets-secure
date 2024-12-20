package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Venta;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("SELECT v FROM Venta v WHERE v.abonado IS NOT NULL and v.ventaNumerada IS NULL and v.ventaZonaGeneral IS NOT NULL")
    public List<Venta> findVentasAbonadosGeneral();

    @Query("SELECT sum(v.ventaZonaGeneral.cantidad) FROM Venta v " +
            "WHERE v.abonado IS NOT NULL and v.ventaNumerada IS NULL and v.ventaZonaGeneral IS NOT NULL " +
            "AND v.ventaZonaGeneral.zonaGeneral.localidad = :localidad " +
            "GROUP BY v.ventaZonaGeneral.zonaGeneral.localidad")
    public Integer totalAbonadosGeneralVendido(@Name("localidad") String localidad);
}
