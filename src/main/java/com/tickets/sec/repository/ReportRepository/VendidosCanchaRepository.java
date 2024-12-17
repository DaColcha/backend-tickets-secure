package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosCanchaView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendidosCanchaRepository extends JpaRepository<VendidosCanchaView, String> {
}
