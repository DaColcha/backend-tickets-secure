package com.tickets.sec.controller;

import com.tickets.sec.model.Entity.SitioVenta;
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

@RestController
@RequestMapping("/sitio-venta")
public class SitioVentaController {

    @Autowired
    private SitioVentaRepository sitioVentaRepository;


    @GetMapping
    public List<SitioVenta> getSitiosVenta() {
        return sitioVentaRepository.findAll();
    }

    @PreAuthorize("hasRole('admin')")
    @SuppressWarnings("null")
    @PostMapping("/crear")
    public ResponseEntity<SitioVenta> createSitioVenta(@RequestBody SitioVenta sitioVenta) {
        sitioVentaRepository.save(sitioVenta);

        return ResponseEntity.ok().build();
    }

}
