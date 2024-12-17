package com.tickets.sec.repository.ReportRepository;

import java.util.List;

import com.tickets.sec.model.Reports.GananciasTotalesView;
import com.tickets.sec.model.Reports.GananciasTotalesViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GananciasTotalesRepository extends JpaRepository<GananciasTotalesView, GananciasTotalesViewId> {

//    @Query(value = "CALL get_abonados_total();", nativeQuery = true)
//    public List<Object> getAbonadosTotales();
}
