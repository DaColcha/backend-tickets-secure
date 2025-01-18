package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosTribunaView;
import com.tickets.sec.model.Reports.VendidosTribunaViewId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la vista de vendidos por tribuna.
 */
public interface VendidosTribunaRepository extends JpaRepository<VendidosTribunaView, VendidosTribunaViewId> {

}