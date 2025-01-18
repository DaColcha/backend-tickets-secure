package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.GananciasTotalesView;
import com.tickets.sec.model.Reports.GananciasTotalesViewId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la vista de ganancias totales.
 */
public interface GananciasTotalesRepository extends JpaRepository<GananciasTotalesView, GananciasTotalesViewId> {

}
