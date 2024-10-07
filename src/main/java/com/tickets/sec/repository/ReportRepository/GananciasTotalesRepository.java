package com.tickets.sec.repository.ReportRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tickets.sec.model.Reports.GananciasTotales;
import com.tickets.sec.model.Reports.GananciasTotalesId;

public interface GananciasTotalesRepository extends JpaRepository<GananciasTotales, GananciasTotalesId> {

    @Query(value = "CALL get_abonados_total();", nativeQuery = true)
    public List<Object> getAbonadosTotales();
}
