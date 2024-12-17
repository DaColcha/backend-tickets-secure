package com.tickets.sec.repository.ReportRepository;

import com.tickets.sec.model.Reports.VendidosTribunaView;
import com.tickets.sec.model.Reports.VendidosTribunaViewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendidosTribunaRepository extends JpaRepository<VendidosTribunaView, VendidosTribunaViewId> {

}