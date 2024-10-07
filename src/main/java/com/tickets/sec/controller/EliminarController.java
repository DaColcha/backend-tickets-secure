package com.tickets.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.model.Compra;
import com.tickets.sec.model.Entity.AbonadosGeneral;
import com.tickets.sec.model.Entity.ZonaGeneral;
import com.tickets.sec.repository.AbonadosGeneralRepository;
import com.tickets.sec.repository.AsientoRepository;
import com.tickets.sec.repository.ZonaGeneralRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/eliminar")
public class EliminarController {

    @Autowired
    private AsientoRepository asientoRepository;
    @Autowired
    private ZonaGeneralRepository zonaGeneralRepository;
    @Autowired
    private AbonadosGeneralRepository abonadosGeneralRepository;

    @Transactional
    @PatchMapping("/{localidad}")
    public ResponseEntity<String> eliminarVendidos(@RequestBody Compra compra, @PathVariable String localidad) {
        asientoRepository.cleanAbonadoSits(localidad.toUpperCase().substring(0, 1),
                compra.getZona(), compra.getTipo(),
                compra.getAsientosSeleccionados());

        return ResponseEntity.ok("Eliminado");
    }

    @Transactional
    @PatchMapping("/general/{id}")
    public ResponseEntity<String> eliminarVendidosGeneral(@PathVariable Integer id) {
        AbonadosGeneral abonado = abonadosGeneralRepository.findByIdCompra(id);
        Integer updateBoletos = zonaGeneralRepository.findByZona(abonado.getZona()).getBoletos()
                + abonado.getCantidadBoletos();

        zonaGeneralRepository.save(new ZonaGeneral(abonado.getZona(), updateBoletos));
        abonadosGeneralRepository.deleteByIdCompra(id);
        return ResponseEntity.ok("Eliminado");
    }
}
