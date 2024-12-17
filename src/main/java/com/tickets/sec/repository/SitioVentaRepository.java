package com.tickets.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SitioVentaRepository extends JpaRepository<SitioVenta, Integer> {

    public SitioVenta findByNombreSitio(String nombreSitio);
}
