package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagoRepository extends JpaRepository<Pago, UUID> {

}
