package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosTotalesView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendidosTotalesRepository extends JpaRepository<VendidosTotalesView, String> {

}
