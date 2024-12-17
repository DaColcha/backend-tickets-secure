package com.tickets.sec.controller;

import java.util.List;

import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.repository.AbonadoRepository;
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
    private AbonadoRepository clienteRepository;

    @GetMapping
    public List<Abonado> getAbonados() {
        return clienteRepository.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<Abonado> createAbonado(@RequestBody Abonado cliente) {
        Abonado creado = this.guardarAbonado(cliente);

        return ResponseEntity.ok().body(creado);
    }

    public Abonado guardarAbonado(Abonado cliente) {
        Abonado clienteExistente = clienteRepository
                .findByCedula(cliente.getCedula());

        if (clienteExistente == null) {
            clienteRepository.save(cliente);
        } else {
            cliente = clienteExistente;
        }

        return cliente;
    }
}
