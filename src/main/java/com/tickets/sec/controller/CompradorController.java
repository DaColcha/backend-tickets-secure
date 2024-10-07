package com.tickets.sec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.model.Entity.Comprador;
import com.tickets.sec.repository.CompradorRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/clientes")
public class CompradorController {

    @Autowired
    private CompradorRepository clienteRepository;

    @GetMapping
    public List<Comprador> getCompradors() {
        return clienteRepository.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<Comprador> createComprador(@RequestBody Comprador cliente) {
        Comprador clienteExistente = clienteRepository
                .findByCorreoCompradorAndNombreComprador(
                        cliente.getCorreoComprador(),
                        cliente.getNombreComprador());

        if (!(clienteExistente != null &&
                clienteExistente.getNombreComprador().equals(cliente.getNombreComprador()) &&
                clienteExistente.getCorreoComprador().equals(cliente.getCorreoComprador()) &&
                clienteExistente.getTelefonoComprador().equals(cliente.getTelefonoComprador()))) {

            Comprador aux = clienteRepository.findTopByOrderByIdCompradorDesc();
            cliente.setIdComprador(aux == null ? 1 : aux.getIdComprador() + 1);
            clienteRepository.save(cliente);
        }

        return ResponseEntity.ok().build();
    }

    public Comprador guardarComprador(Comprador cliente) {
        Comprador clienteExistente = clienteRepository
                .findByCorreoCompradorAndNombreComprador(
                        cliente.getCorreoComprador(),
                        cliente.getNombreComprador());

        if (!(clienteExistente != null &&
                clienteExistente.getNombreComprador().equals(cliente.getNombreComprador()) &&
                clienteExistente.getCorreoComprador().equals(cliente.getCorreoComprador()) &&
                clienteExistente.getTelefonoComprador().equals(cliente.getTelefonoComprador()))) {

            Comprador aux = clienteRepository.findTopByOrderByIdCompradorDesc();
            cliente.setIdComprador(aux == null ? 1 : aux.getIdComprador() + 1);
            clienteRepository.save(cliente);
        } else {
            cliente = clienteExistente;
        }

        return cliente;
    }
}
