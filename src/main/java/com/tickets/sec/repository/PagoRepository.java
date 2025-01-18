package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio de la entidad Pago
 */
public interface PagoRepository extends JpaRepository<Pago, UUID> {

}
