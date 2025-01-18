package com.tickets.sec.controller;

import java.util.List;

import com.tickets.sec.model.Entity.AsientosNumerado;
import com.tickets.sec.model.Entity.ZonaGeneral;
import com.tickets.sec.repository.AsientosRepository;
import com.tickets.sec.repository.VentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

/**
 * Controlador para la gestión de Asientos Numerados
 */
@Slf4j
@RestController
@RequestMapping("/asiento")
public class AsientoController {

    @Autowired
    private AsientosRepository asientoRepository;

    @Autowired
    private ZonaGeneralRepository zonaGeneralRepository;
    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Obtiene los asientos por localidad, zona y tipo
     *
     * @param localidad T - Tribuna, C - Cancha
     * @param zona     A0/A1/A2, B0/B1/B2, C0, D0
     * @param tipo      SILLAS, BUTACA, GRADAS
     * @return List<AsientosNumerado> lista de asientos que corresponden a los parámetros.
     */
    @GetMapping("/{localidad}/{zona}/{tipo}")
    public List<AsientosNumerado> getSitsByZone(@PathVariable String localidad, @PathVariable String zona,
            @PathVariable String tipo) {
        return asientoRepository.findByLocalidadAndZonaAndTipo(localidad, zona, tipo);
    }

    /**
     * Obtiene la cantidad de asientos disponibles por localidad, zona y tipo
     *
     * @param localidad T - Tribuna, C - Cancha
     * @param zona     A0/A1/A2, B0/B1/B2, C0, D0
     * @param tipo      SILLAS, BUTACA, GRADAS
     * @return int cantidad de asientos disponibles
     */
    @GetMapping("/{localidad}/{zona}/{tipo}/disponible")
    public int getAvailableSitsByZone(@PathVariable String localidad, @PathVariable String zona,
            @PathVariable String tipo) {
        return asientoRepository.zoneAvailable(localidad, zona, tipo);
    }

    /**
     * Modifica el estado de los asientos que NO hayan sido comprados por Abonados a Disponible
     *
     * @return ResponseEntity<String> con mensaje de éxito
     */
    @PreAuthorize("hasRole('admin')")
    @Transactional
    @PatchMapping("/limpiar")
    public ResponseEntity<String> clearAllSitsNoAbonated() {

        asientoRepository.cleanAllSitsNoAbonate();

        Integer boletosA = ventaRepository.totalAbonadosGeneralVendido("A");
        Integer boletosB = ventaRepository.totalAbonadosGeneralVendido("B");

        boletosA = (boletosA != null) ? boletosA : 0;
        boletosB = (boletosB != null) ? boletosB : 0;

        zonaGeneralRepository.save(new ZonaGeneral("A", 1500 - boletosA));
        zonaGeneralRepository.save(new ZonaGeneral("B", 1500 - boletosB));

        log.info("Se han limpiado los asientos no abonados");
        return ResponseEntity.ok().build();
    }

    /**
     * Modifica el estado de todos los asientos a Disponible
     *
     * @return ResponseEntity<String> con mensaje de éxito
     */
    @PreAuthorize("hasRole('admin')")
    @Transactional
    @PatchMapping("/limpiar-todo")
    public ResponseEntity<String> clearAllSits() {
        asientoRepository.cleanAllSits();
        zonaGeneralRepository.restartGeneral();

        log.info("Se han limpiado todos los asientos");
        return ResponseEntity.ok().build();
    }
}
