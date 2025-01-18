package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosGeneralView;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite la interacción con la base de datos de la vista de vendidos general.
 */
public interface VendidosGeneralRepository extends JpaRepository<VendidosGeneralView, String> {
}
