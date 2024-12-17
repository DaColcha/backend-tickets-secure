package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosGeneralView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendidosGeneralRepository extends JpaRepository<VendidosGeneralView, String> {
}
