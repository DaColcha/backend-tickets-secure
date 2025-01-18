package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.SitioVenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad SitioVenta
 */
public interface SitioVentaRepository extends JpaRepository<SitioVenta, Integer> {

}
