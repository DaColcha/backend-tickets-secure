package com.tickets.sec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.repository.ReportRepository.GananciasTotalesRepository;
import com.tickets.sec.repository.ReportRepository.VendidosCanchaRepository;
import com.tickets.sec.repository.ReportRepository.VendidosGeneralRepository;
import com.tickets.sec.repository.ReportRepository.VendidosTotalesRepository;
import com.tickets.sec.repository.ReportRepository.VendidosTribunaRepository;
import com.tickets.sec.model.Reports.*;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase que se encarga de manejar las peticiones de reportes
 */
@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private GananciasTotalesRepository gananciasTotalesRepository;
    @Autowired
    private VendidosTotalesRepository vendidosTotalesRepository;
    @Autowired
    private VendidosCanchaRepository vendidosCanchaRepository;
    @Autowired
    private VendidosTribunaRepository vendidosTribunaRepository;
    @Autowired
    private VendidosGeneralRepository vendidosGeneralRepository;

    /**
     * Obtiene los boletos vendidos totales por localidad
     * @return Lista de boletos vendidos totales
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/vendidos/total")
    public List<VendidosTotalesView> getVendidosTotal() {
        return vendidosTotalesRepository.findAll();
    }

    /**
     * Obtiene los boletos vendidos en Cancha
     * @return Lista de boletos vendidos por zona.
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/vendidos/cancha")
    public List<VendidosCanchaView> getMethodName() {
        return vendidosCanchaRepository.findAll();
    }

    /**
     * Obtiene los boletos vendidos en Tribuna
     * @return Lista de boletos vendidos por zona.
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/vendidos/tribuna")
    public List<VendidosTribunaView> getVendidosTribuna() {
        return vendidosTribunaRepository.findAll();
    }

    /**
     * Obtiene los boletos vendidos en General
     * @return Lista de boletos vendidos por zona.
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/vendidos/general")
    public List<VendidosGeneralView> getVendidosGeneral() {
        return vendidosGeneralRepository.findAll();
    }

    /**
     * Obtiene las ganancias totales
     * @return Lista de ganancias totales
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/ganancias")
    public List<GananciasTotalesView> getGananciasTotales() {
        return gananciasTotalesRepository.findAll();
    }

}
