package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosTribuna;
import com.tickets.sec.model.Reports.VendidosTribunaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendidosTribunaRepository extends JpaRepository<VendidosTribuna, VendidosTribunaId> {

}