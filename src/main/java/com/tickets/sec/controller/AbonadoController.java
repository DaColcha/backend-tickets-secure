package com.tickets.sec.controller;

import java.util.List;

import com.tickets.sec.dto.AbonadoResponse;
import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.repository.AbonadoRepository;
import com.tickets.sec.service.AbonadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/clientes")
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
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/crear")
    public ResponseEntity<Abonado> createAbonado(@RequestBody Abonado cliente) {
        Abonado creado = abonadoService.guardarAbonado(cliente);

        return ResponseEntity.ok().body(creado);
    }

}
