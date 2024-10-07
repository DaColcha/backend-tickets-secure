package com.tickets.sec.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.model.Entity.AbonadosGeneral;
import com.tickets.sec.model.Entity.SitioVenta;
import com.tickets.sec.model.Entity.ZonaGeneral;
import com.tickets.sec.repository.AbonadosGeneralRepository;
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

@RestController
@RequestMapping("/general")
public class ZonaGeneralController {

    @Autowired
    private CompradorController compradorController;
    @Autowired
    private ZonaGeneralRepository zonaGeneralRepository;
    @Autowired
    private AbonadosGeneralRepository abonadosGeneralRepository;
    @Autowired
    private SitioVentaRepository sitioVentaRepository;

    @GetMapping("/{zona}")
    public Integer getDisponibilidad(@PathVariable String zona) {
        return zonaGeneralRepository.findByZona(zona).getBoletos();
    }

    @PostMapping("/compra")
    public ResponseEntity<String> compraGeneral(@RequestBody ZonaGeneral zonaGeneralCompra) {
        Integer boletosComprados = zonaGeneralCompra.getBoletos();
        Integer boletosDisponibles = getDisponibilidad(zonaGeneralCompra.getZona());
        if (boletosDisponibles >= boletosComprados) {
            zonaGeneralCompra.setBoletos(boletosDisponibles - boletosComprados);
            zonaGeneralRepository.save(zonaGeneralCompra);
            return ResponseEntity.ok("Compra exitosa");
        } else {
            return ResponseEntity.badRequest().body("No hay suficientes boletos");
        }

    }

    @SuppressWarnings("null")
    @PostMapping("/compra/abonado")
    public ResponseEntity<String> compraGeneralAbonado(@RequestBody AbonadosGeneral compra) {

        SitioVenta sitioVenta = sitioVentaRepository.findByNombreSitio(compra.getSitioVenta().getNombreSitio());

        Integer boletosComprados = compra.getCantidadBoletos();
        Integer boletosDisponibles = getDisponibilidad(compra.getZona());
        ZonaGeneral updateZona = new ZonaGeneral();

        if (boletosDisponibles >= boletosComprados) {
            compra.setComprador(compradorController.guardarComprador(compra.getComprador()));
            AbonadosGeneral idAux = abonadosGeneralRepository.findTopByOrderByIdCompraDesc();
            Integer id = (idAux != null) ? idAux.getIdCompra() + 1 : 1;

            AbonadosGeneral abonado = new AbonadosGeneral(
                    id,
                    compra.getZona(),
                    compra.getComprador(),
                    boletosComprados,
                    sitioVenta,
                    compra.getPago(),
                    compra.getPlazo());

            updateZona.setZona(compra.getZona());
            updateZona.setBoletos(boletosDisponibles - boletosComprados);
            zonaGeneralRepository.save(updateZona);
            abonadosGeneralRepository.save(abonado);

            return ResponseEntity.ok("Compra exitosa");
        } else {
            return ResponseEntity.badRequest().body("No hay suficientes boletos");
        }
    }

    @Transactional
    @PatchMapping("/limpiar-todo")
    public ResponseEntity<String> limpiarGeneralTodo() {
        zonaGeneralRepository.restartGeneral();
        abonadosGeneralRepository.cleanAbonadosGeneral();
        return ResponseEntity.ok("Limpieza exitosa");
    }

    @Transactional
    @PatchMapping("/limpiar")
    public ResponseEntity<String> limpiarGeneral() {
        Integer boletosA = abonadosGeneralRepository.cantidadBoletosAbonadosByZona("A");
        Integer boletosB = abonadosGeneralRepository.cantidadBoletosAbonadosByZona("B");

        boletosA = (boletosA != null) ? boletosA : 0;
        boletosB = (boletosB != null) ? boletosB : 0;

        zonaGeneralRepository.save(new ZonaGeneral("A", 1500 - boletosA));
        zonaGeneralRepository.save(new ZonaGeneral("B", 1500 - boletosB));

        return ResponseEntity.ok("Limpieza exitosa");
    }

}
