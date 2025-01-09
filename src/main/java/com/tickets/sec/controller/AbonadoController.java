package com.tickets.sec.controller;

import java.util.List;

import com.tickets.sec.dto.AbonadoResponse;
import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.repository.AbonadoRepository;
import com.tickets.sec.service.AbonadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class AbonadoController {

    @Autowired
    private AbonadoService abonadoService;

    @GetMapping
    public List<Abonado> getAbonados() {
        return abonadoService.findAll();
    }

    @GetMapping("/validar")
    public ResponseEntity<AbonadoResponse> getAbonadoById(@RequestBody String cedula) {
        AbonadoResponse cliente = abonadoService.findByCedula(cedula);

        if (cliente == null) {
            log.info("No se encontró el cliente abonado con la cédula proporcionada");
            return ResponseEntity.notFound().build();
        }

        log.info("Se ha validado correctamente al cliente abonado con la cédula proporcionada");
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/crear")
    public ResponseEntity<Abonado> createAbonado(@RequestBody Abonado cliente) {
        Abonado creado = abonadoService.guardarAbonado(cliente);

        log.info("Se ha creado un nuevo cliente abonado");
        return ResponseEntity.ok().body(creado);
    }

}
