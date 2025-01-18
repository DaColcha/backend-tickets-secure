package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Venta;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para la entidad Venta
 */
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    /**
     * Método que devuelve todas las ventas de tipo Abonado General
     * @return
     */
    @Query("SELECT Venta" +
            "    FROM Venta v" +
            "    WHERE v.tipoVenta = 'A' AND v.ventaZonaGeneral IS NOT NULL AND v.ventaNumerada IS NULL")
    public List<Venta> findVentasAbonadosGeneral();

    /**
     * Método que devuelve el total de boletos ventidos a abonados para la zona general
     * @param localidad
     * @return
     */
    @Query("SELECT sum(vg.cantidad)" +
            "    FROM Venta v" +
            "    JOIN VentasZonaGeneral vg ON v.ventaZonaGeneral.id = vg.id" +
            "    JOIN ZonaGeneral zg ON vg.zonaGeneral.id = zg.id" +
            "    WHERE v.tipoVenta = 'A' and zg.localidad = :localidad")
    public Integer totalAbonadosGeneralVendido(@Name("localidad") String localidad);

    /**
     * Método que devuelve todas las ventas de asientos numerados para Abonados según la localidad
     * @return
     */
    @Query("SELECT DISTINCT Venta " +
            "FROM Venta v " +
            "JOIN VentasAsientosNumerado va ON v.ventaNumerada.id = va.id " +
            "JOIN AsientosNumerado an ON an.id = ANY(va.asientos)" +
            "WHERE an.localidad = ':localidad' and v.tipoVenta = 'A'")
    public List<Venta> findVentasAbonadosNumerados(@Name("localidad") String localidad);
}