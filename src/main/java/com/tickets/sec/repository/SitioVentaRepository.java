package com.tickets.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.sec.model.Entity.SitioVenta;

public interface SitioVentaRepository extends JpaRepository<SitioVenta, Integer> {

    public SitioVenta findByNombreSitio(String nombreSitio);
}
