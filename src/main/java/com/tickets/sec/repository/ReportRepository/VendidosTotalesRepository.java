package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosTotalesView;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la vista de vendidos totales.
 */
public interface VendidosTotalesRepository extends JpaRepository<VendidosTotalesView, String> {

}
