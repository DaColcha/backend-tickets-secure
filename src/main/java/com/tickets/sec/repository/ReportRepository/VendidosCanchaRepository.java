package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosCanchaView;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite la interacción con la base de datos de la vista de vendidos en cancha.
 */
public interface VendidosCanchaRepository extends JpaRepository<VendidosCanchaView, String> {
}
