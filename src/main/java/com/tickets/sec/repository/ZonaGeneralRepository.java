package com.tickets.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tickets.sec.model.Entity.ZonaGeneral;

public interface ZonaGeneralRepository extends JpaRepository<ZonaGeneral, String> {

    public ZonaGeneral findByZona(String zona);

    @Modifying
    @Query("UPDATE ZonaGeneral SET boletos=1500")
    public void restartGeneral();
}
