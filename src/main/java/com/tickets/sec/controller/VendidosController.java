package com.tickets.sec.controller;

import org.springframework.web.bind.annotation.RestController;
import com.tickets.sec.model.ReporteComprado;
import com.tickets.sec.model.ReporteGeneral;
import com.tickets.sec.model.ReporteGeneralAbonado;
import com.tickets.sec.repository.AbonadosGeneralRepository;
import com.tickets.sec.repository.AsientoRepository;
import com.tickets.sec.repository.ZonaGeneralRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/vendidos")
public class VendidosController {

    @Autowired
    private AsientoRepository asientoRepository;
    @Autowired
    private ZonaGeneralRepository zonaGeneralRepository;
    @Autowired
    private AbonadosGeneralRepository abonadosGeneralRepository;

    private ReporteComprado reportesComprado = new ReporteComprado();

    @GetMapping("/total")
    public int getTotalVendidos() {
        int totalNumerados = asientoRepository.totalVendidos();
        int totalGeneralA = 1500 - zonaGeneralRepository.findByZona("A").getBoletos();
        int totalGeneralB = 1500 - zonaGeneralRepository.findByZona("B").getBoletos();

        return totalGeneralA + totalGeneralB + totalNumerados;
    }

    @GetMapping("/{localidad}/total")
    public int getTotalVendidosByZona(@PathVariable String localidad) {
        return asientoRepository.totalVendidosLocalidad(localidad.toUpperCase().substring(0, 1));
    }

    @GetMapping("/{localidad}")
    public List<ReporteComprado> getVendidosByZona(@PathVariable String localidad) {
        return reportesComprado.createReport(
                asientoRepository.findAbonadoByLocalidad(localidad.toUpperCase().substring(0, 1)));
    }

    @GetMapping("/abonados")
    public List<ReporteComprado> getAbonados() {
        return reportesComprado.createReport(asientoRepository.findAbonados());
    }

    @GetMapping("/abonados/total")
    public Integer getTotalAbonados() {
        return asientoRepository.totalAbonados();
    }

    @GetMapping("/general/{zona}")
    public ReporteGeneral getVendidos(@PathVariable String zona) {
        Integer disponibles = zonaGeneralRepository.findByZona(zona).getBoletos();
        Integer vendidos = 1500 - disponibles;
        return new ReporteGeneral(disponibles, vendidos);
    }

    @GetMapping("/general")
    public List<ReporteGeneralAbonado> getVendidos() {
        ReporteGeneralAbonado reporte = new ReporteGeneralAbonado();
        return reporte.createReport(abonadosGeneralRepository.findAll());
    }
}