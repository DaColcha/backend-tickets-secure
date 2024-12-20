package com.tickets.sec.controller;

import com.tickets.sec.model.Entity.SitioVenta;
import com.tickets.sec.model.Entity.Venta;
import com.tickets.sec.model.Entity.ZonaGeneral;
import com.tickets.sec.repository.VentaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.repository.SitioVentaRepository;
import com.tickets.sec.repository.ZonaGeneralRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@RestController
@RequestMapping("/general")
public class ZonaGeneralController {

    @Autowired
    private AbonadoController abonadoController;
    @Autowired
    private ZonaGeneralRepository zonaGeneralRepository;
    @Autowired
    private SitioVentaRepository sitioVentaRepository;
    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping("/{zona}")
    public Integer getDisponibilidad(@PathVariable String zona) {
        return zonaGeneralRepository.findByLocalidad(zona).getDisponibles();
    }

    @PostMapping("/compra")
    public ResponseEntity<String> compraGeneral(@RequestBody ZonaGeneral zonaGeneralCompra) {
        Integer boletosComprados = zonaGeneralCompra.getDisponibles();
        Integer boletosDisponibles = getDisponibilidad(zonaGeneralCompra.getLocalidad());
        if (boletosDisponibles >= boletosComprados) {
            zonaGeneralCompra.setDisponibles(boletosDisponibles - boletosComprados);
            zonaGeneralRepository.save(zonaGeneralCompra);
            return ResponseEntity.ok("Compra exitosa");
        } else {
            return ResponseEntity.badRequest().body("No hay suficientes boletos");
        }

    }

    @SuppressWarnings("null")
    @PostMapping("/compra/abonado")
    public ResponseEntity<String> compraGeneralAbonado(@RequestBody Venta compra) {

        SitioVenta sitioVenta = sitioVentaRepository.findByNombre(compra.getVendedor().getSitioVenta().getNombre());

        Integer boletosComprados = compra.getVentaZonaGeneral().getCantidad();
        Integer boletosDisponibles = getDisponibilidad(compra.getVentaZonaGeneral().getZonaGeneral().getLocalidad());


        if (boletosDisponibles >= boletosComprados) {
            compra.setAbonado(abonadoController.guardarAbonado(compra.getAbonado()));


            Venta ventaGeneral = new Venta(
                    null,
                    compra.getVentaZonaGeneral(),
                    compra.getPago(),
                    compra.getVendedor(),
                    compra.getAbonado(),
                    new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
                    compra.getTotalVenta()
            );

            ZonaGeneral updateZona = new ZonaGeneral();
            updateZona.setLocalidad(compra.getVentaZonaGeneral().getZonaGeneral().getLocalidad());
            updateZona.setDisponibles(boletosDisponibles - boletosComprados);
            zonaGeneralRepository.save(updateZona);
            ventaRepository.save(ventaGeneral);

            return ResponseEntity.ok("Compra exitosa");
        } else {
            return ResponseEntity.badRequest().body("No hay suficientes boletos");
        }
    }

    @Transactional
    @PatchMapping("/limpiar-todo")
    public ResponseEntity<String> limpiarGeneralTodo() {
        zonaGeneralRepository.restartGeneral();
        return ResponseEntity.ok("Limpieza exitosa");
    }

    @Transactional
    @PatchMapping("/limpiar")
    public ResponseEntity<String> limpiarGeneral() {
        Integer boletosA = ventaRepository.totalAbonadosGeneralVendido("A");
        Integer boletosB = ventaRepository.totalAbonadosGeneralVendido("B");

        boletosA = (boletosA != null) ? boletosA : 0;
        boletosB = (boletosB != null) ? boletosB : 0;

        zonaGeneralRepository.save(new ZonaGeneral("A", 1500 - boletosA));
        zonaGeneralRepository.save(new ZonaGeneral("B", 1500 - boletosB));

        return ResponseEntity.ok("Limpieza exitosa");
    }

}
