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
    public List<VendidosTotales> getVendidosTotal() {
        return vendidosTotalesRepository.findAll();
    }

    @GetMapping("/vendidos/cancha")
    public List<VendidosCancha> getMethodName() {
        return vendidosCanchaRepository.findAll();
    }

    @GetMapping("/vendidos/tribuna")
    public List<VendidosTribuna> getVendidosTribuna() {
        return vendidosTribunaRepository.findAll();
    }

    @GetMapping("/vendidos/general")
    public List<VendidosGeneral> getVendidosGeneral() {
        return vendidosGeneralRepository.findAll();
    }

    @GetMapping("/ganancias")
    public List<GananciasTotales> getGananciasTotales() {
        return gananciasTotalesRepository.findAll();
    }

    @GetMapping("/abonados")
    public List<AbonadosTotales> getAbonadosTotales() {
        List<Object> procedureResult = gananciasTotalesRepository.getAbonadosTotales();

        List<AbonadosTotales> abonadosTotales = new ArrayList<>();
        for (Object object : procedureResult) {
            Object[] fields = (Object[]) object;
            abonadosTotales.add(new AbonadosTotales((String) fields[0], (BigDecimal) fields[1]));
        }

        return abonadosTotales;
    }

}
