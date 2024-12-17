package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.ZonaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ZonaGeneralRepository extends JpaRepository<ZonaGeneral, String> {

    public ZonaGeneral findByZona(String zona);

    @Modifying
    @Query("UPDATE ZonaGeneral SET disponibles=1500")
    public void restartGeneral();
}
