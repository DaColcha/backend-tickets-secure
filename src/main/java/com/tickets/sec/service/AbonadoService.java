package com.tickets.sec.service;

import com.tickets.sec.dto.AbonadoResponse;
import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.repository.AbonadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que contiene la logica de negocio para el manejo de los abonados
 */
@Service
@Slf4j
public class AbonadoService {

    @Autowired
    private AbonadoRepository clienteRepository;

    /**
     * Metodo que retorna todos los abonados existentes
     * @return Lista de abonados
     */
    public List<Abonado> findAll() {
        return clienteRepository.findAll();
    }

    /**
     * Metodo que guarda un abonado en la base de datos
     * @param cliente
     * @return Abonado
     */
    public Abonado guardarAbonado(Abonado cliente) {
        Abonado clienteExistente = clienteRepository
                .findByCedula(cliente.getCedula());

        if (clienteExistente == null) {
            clienteRepository.save(cliente);
            log.info("Nuevo abonado registrado.");
        } else {
            cliente = clienteExistente;
        }

        return cliente;
    }

    /**
     * Metodo que retorna un abonado por su cedula
     * @param id
     * @return
     */
    public AbonadoResponse findByCedula(String id) {
        Abonado cliente = clienteRepository.findByCedula(id);

        log.info("Se ha realizado una busqueda de usuario por cedula.");
        return new AbonadoResponse(cliente.getCedula(),
                cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono());
    }
}
