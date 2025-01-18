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

/**
 * Controlador para la gestión de clientes abonados
 */
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

    /**
     * Valida si un cliente abonado existe en la base de datos
     *
     * @param cedula Cédula del cliente abonado
     * @return ResponseEntity con el cliente abonado si existe, o un ResponseEntity vacío.
     */
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

    /**
     * Crea un nuevo cliente abonado en la base de datos
     *
     * @param cliente Cliente abonado a crear
     * @return ResponseEntity con el cliente abonado creado
     */
    @PostMapping("/crear")
    public ResponseEntity<Abonado> createAbonado(@RequestBody Abonado cliente) {
        Abonado creado = abonadoService.guardarAbonado(cliente);

        log.info("Se ha creado un nuevo cliente abonado");
        return ResponseEntity.ok().body(creado);
    }

}
