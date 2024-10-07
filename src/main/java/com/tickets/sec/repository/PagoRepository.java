package com.tickets.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.sec.model.Entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

}
