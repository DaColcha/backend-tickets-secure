package com.tickets.sec.controller;

import com.tickets.sec.model.Entity.SitioVenta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.repository.SitioVentaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador que maneja peticiones sobre SitioVenta
 */
@RestController
@RequestMapping("/sitio-venta")
@Slf4j
public class SitioVentaController {

    @Autowired
    private SitioVentaRepository sitioVentaRepository;


    /**
     * Obtiene todos los sitios de venta
     * @return Lista de sitios de venta
     */
    @GetMapping
    public List<SitioVenta> getSitiosVenta() {
        return sitioVentaRepository.findAll();
    }

    /**
     * Crea un sitio de venta
     * @param sitioVenta
     * @return ResponseEntity con el resultado de la operacion
     */
    @PreAuthorize("hasRole('admin')")
    @SuppressWarnings("null")
    @PostMapping("/crear")
    public ResponseEntity<SitioVenta> createSitioVenta(@RequestBody SitioVenta sitioVenta) {
        sitioVentaRepository.save(sitioVenta);
        log.info("Sitio de venta creado con exito");
        return ResponseEntity.ok().build();
    }

}
