package com.tickets.sec.controller;

import java.util.List;

import com.tickets.sec.model.Entity.AsientosNumerado;
import com.tickets.sec.model.Entity.ZonaGeneral;
import com.tickets.sec.repository.AsientosRepository;
import com.tickets.sec.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.dto.CompraNumerados;
import com.tickets.sec.repository.ZonaGeneralRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/asiento")
public class AsientoController {

    @Autowired
    private AsientosRepository asientoRepository;

    @Autowired
    private ZonaGeneralRepository zonaGeneralRepository;
    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping("/{localidad}/{zona}/{tipo}")
    public List<AsientosNumerado> getSitsByZone(@PathVariable String localidad, @PathVariable String zona,
            @PathVariable String tipo) {
        return asientoRepository.findByLocalidadAndZonaAndTipo(localidad, zona, tipo);
    }

    @GetMapping("/{localidad}/{zona}/{tipo}/disponible")
    public int getAvailableSitsByZone(@PathVariable String localidad, @PathVariable String zona,
            @PathVariable String tipo) {
        return asientoRepository.zoneAvailable(localidad, zona, tipo);
    }

    @SuppressWarnings("null")
    @PostMapping("/create-sits")
    public ResponseEntity<AsientosNumerado> createSits(@RequestBody List<AsientosNumerado> asientos) {
        asientoRepository.saveAll(asientos);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PatchMapping("/limpiar-no-abonado")
    public ResponseEntity<String> clearSitNoAbonated(@RequestBody CompraNumerados compraNoAbonado) {

        asientoRepository.cleanSitNoAbonate(compraNoAbonado.getLocalidad(),
                compraNoAbonado.getZona(),
                compraNoAbonado.getTipo(),
                compraNoAbonado.getAsientosSeleccionados());

        return ResponseEntity.ok("Asientos eliminados ");
    }

    @Transactional
    @PatchMapping("/limpiar")
    public ResponseEntity<String> clearAllSitsNoAbonated() {

        //TODO:LIMPIAR ASIENTOS NUMERADOS DE NO ABONADOS
        //asientoRepository.cleanAllSitsNoAbonate();

        Integer boletosA = ventaRepository.totalAbonadosGeneralVendido("A");
        Integer boletosB = ventaRepository.totalAbonadosGeneralVendido("B");

        boletosA = (boletosA != null) ? boletosA : 0;
        boletosB = (boletosB != null) ? boletosB : 0;

        zonaGeneralRepository.save(new ZonaGeneral("A", 1500 - boletosA));
        zonaGeneralRepository.save(new ZonaGeneral("B", 1500 - boletosB));

        return ResponseEntity.ok().build();
    }

    @Transactional
    @PatchMapping("/limpiar-todo")
    public ResponseEntity<String> clearAllSits() {
        asientoRepository.cleanAllSits();
        zonaGeneralRepository.restartGeneral();
        return ResponseEntity.ok().build();
    }
}
