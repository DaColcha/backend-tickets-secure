package com.tickets.sec.controller;

import com.tickets.sec.repository.AsientosRepository;
import com.tickets.sec.repository.VentaRepository;
import com.tickets.sec.service.ReporteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import com.tickets.sec.dto.ReporteNumeradosAbonado;
import com.tickets.sec.dto.ReporteGeneral;
import com.tickets.sec.dto.ReporteGeneralAbonado;
import com.tickets.sec.repository.ZonaGeneralRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador de peticiones relacionadas con los asientos vendidos
 */
@RestController
@RequestMapping("/vendidos")
public class VendidosController {

    @Autowired
    private AsientosRepository asientoRepository;
    @Autowired
    private ZonaGeneralRepository zonaGeneralRepository;

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Obtiene el total de asientos vendidos en todas las zonas
     * @return integer con el total de asientos vendidos
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/total")
    public int getTotalVendidos() {
        int totalNumerados = asientoRepository.totalVendidos();
        int totalGeneralA = 1500 - zonaGeneralRepository.findByLocalidad("A").getDisponibles();
        int totalGeneralB = 1500 - zonaGeneralRepository.findByLocalidad("B").getDisponibles();

        return totalGeneralA + totalGeneralB + totalNumerados;
    }

    /**
     * Obtiene el total de asientos vendidos en una localidad
     * @param localidad localidad de la que se quiere obtener el total de asientos vendidos
     * @return integer con el total de asientos vendidos en la localidad
     */
    @GetMapping("/{localidad}/total")
    public int getTotalVendidosByZona(@PathVariable String localidad) {
        return asientoRepository.totalVendidosLocalidad(localidad.toUpperCase().substring(0, 1));
    }

    /**
     * Obtiene informacion relevante sobre la venta de asientos según la localidad
     * @param localidad localidad de la que se quiere obtener los asientos vendidos
     * @return lista de asientos vendidos en la localidad
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{localidad}")
    public List<ReporteNumeradosAbonado> getVendidosByZona(@PathVariable String localidad) {
        return reporteService.createReport(ventaRepository.findVentasAbonadosNumerados(localidad.toUpperCase().substring(0, 1)));
    }


    /**
     * Obtiene informacion relevante sobre la venta de boletos en General según la zona
     * @param zona zona de la que se quiere obtener el total de asientos vendidos
     * @return objeto con el total de asientos vendidos y disponibles
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/general/{zona}")
    public ReporteGeneral getVendidos(@PathVariable String zona) {
        Integer disponibles = zonaGeneralRepository.findByLocalidad(zona).getDisponibles();
        Integer vendidos = 1500 - disponibles;
        return new ReporteGeneral(disponibles, vendidos);
    }

    /**
     * Obtiene informacion relevante sobre la venta de boletos en General
     * @return lista de asientos vendidos en General
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/general")
    public List<ReporteGeneralAbonado> getVendidos() {
        return reporteService.createReportGeneral(ventaRepository.findVentasAbonadosGeneral());
    }
}
