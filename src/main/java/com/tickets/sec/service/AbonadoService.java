package com.tickets.sec.service;

import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.repository.AbonadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AbonadoService {

    @Autowired
    private AbonadoRepository clienteRepository;


    public List<Abonado> findAll() {
        return clienteRepository.findAll();
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
