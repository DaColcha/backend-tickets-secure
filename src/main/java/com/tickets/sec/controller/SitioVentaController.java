package com.tickets.sec.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.model.Entity.SitioVenta;
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

    @PostMapping("/login")
    public ResponseEntity<String> loginSitio(@RequestBody SitioVenta sitioVenta) {
        SitioVenta sitio = sitioVentaRepository.findByNombreSitio(sitioVenta.getNombreSitio());
        if (sitio != null) {
            if (sitio.getContraseña().equals(sitioVenta.getContraseña())) {
                return ResponseEntity.ok(sitio.getRol());
            } else {
                return ResponseEntity.badRequest().body("Contraseña incorrecta");
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @SuppressWarnings("null")
    @PostMapping("/crear")
    public ResponseEntity<SitioVenta> createSitioVenta(@RequestBody SitioVenta sitioVenta) {
        sitioVentaRepository.save(sitioVenta);

        return ResponseEntity.ok().build();
    }

}
