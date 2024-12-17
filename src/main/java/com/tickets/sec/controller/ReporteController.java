package com.tickets.sec.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.repository.ReportRepository.GananciasTotalesRepository;
import com.tickets.sec.repository.ReportRepository.VendidosCanchaRepository;
import com.tickets.sec.repository.ReportRepository.VendidosGeneralRepository;
import com.tickets.sec.repository.ReportRepository.VendidosTotalesRepository;
import com.tickets.sec.repository.ReportRepository.VendidosTribunaRepository;
import com.tickets.sec.model.Reports.*;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/vendidos/total")
    public List<VendidosTotalesView> getVendidosTotal() {
        return vendidosTotalesRepository.findAll();
    }

    @GetMapping("/vendidos/cancha")
    public List<VendidosCanchaView> getMethodName() {
        return vendidosCanchaRepository.findAll();
    }

    @GetMapping("/vendidos/tribuna")
    public List<VendidosTribunaView> getVendidosTribuna() {
        return vendidosTribunaRepository.findAll();
    }

    @GetMapping("/vendidos/general")
    public List<VendidosGeneralView> getVendidosGeneral() {
        return vendidosGeneralRepository.findAll();
    }

    @GetMapping("/ganancias")
    public List<GananciasTotalesView> getGananciasTotales() {
        return gananciasTotalesRepository.findAll();
    }

}
