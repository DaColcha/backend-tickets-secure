package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.ZonaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio para la entidad ZonaGeneral
 */
public interface ZonaGeneralRepository extends JpaRepository<ZonaGeneral, String> {

    /**
     * Buscar una zona general por localidad
     * @param localidad
     * @return
     */
    public ZonaGeneral findByLocalidad(String localidad);

    /**
     * Actualizar la cantidad de boletos disponibles en la zona general
     */
    @Modifying
    @Query("UPDATE ZonaGeneral SET disponibles=1500")
    public void restartGeneral();
}
