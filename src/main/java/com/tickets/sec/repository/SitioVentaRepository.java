package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.SitioVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SitioVentaRepository extends JpaRepository<SitioVenta, Integer> {

    public SitioVenta findByNombre(String nombreSitio);
}
