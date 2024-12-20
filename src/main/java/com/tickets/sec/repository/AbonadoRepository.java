package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Abonado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbonadoRepository extends JpaRepository<Abonado, Integer> {

    public Abonado findByCedula(String cedula);
}
