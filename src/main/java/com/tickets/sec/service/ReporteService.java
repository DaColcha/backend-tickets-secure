package com.tickets.sec.service;

import com.tickets.sec.dto.ReporteNumeradosAbonado;
import com.tickets.sec.dto.ReporteGeneralAbonado;
import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.model.Entity.AsientosNumerado;
import com.tickets.sec.model.Entity.Venta;
import com.tickets.sec.repository.AsientosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la generación de reportes
 */
@Service
@Transactional
@Slf4j
public class ReporteService {

    @Autowired
    private AsientosRepository asientosRepository;

    /**
     * Crea un reporte detallado de la venta de boletos abonados basado en una lista de ventas proporcionada
     * @param ventas
     * @return
     */
    public List<ReporteNumeradosAbonado> createReport(List<Venta> ventas) {
        List<ReporteNumeradosAbonado> vendidos = new ArrayList<>();

        List<Abonado> compradores = ventas.stream().map(v -> v.getAbonado()).distinct().toList();

        Integer count = 1;
        for (Abonado c : compradores) {
            List<Venta> ventasAbonado = ventas.stream().filter(v -> v.getAbonado().equals(c)).toList();

            for(Venta v : ventasAbonado) {

                List<AsientosNumerado> asientos = asientosRepository.findByIdList(v.getVentaNumerada().getAsientos());

                ReporteNumeradosAbonado reporte = new ReporteNumeradosAbonado(
                        count,
                        asientos.get(0).getZona(),
                        asientos.get(0).getTipo(),
                        v.getVentaNumerada().getAsientos(), c,
                        v.getTipoVenta(),
                        v.getVendedor().getSitioVenta().getNombre(),
                        v.getPago().getFormapago().getFormaPago());

                vendidos.add(reporte);
                count++;
            }
        }

        return vendidos;
    }

    /**
     * Crea un reporte de boletos de General Abonados basado en una lista de ventas proporcionada
     * @param ventasZonaGeneralList
     * @return
     */
    public List<ReporteGeneralAbonado> createReportGeneral(List<Venta> ventasZonaGeneralList) {
        List<ReporteGeneralAbonado> reporte = new ArrayList<>();
        for (Venta venta : ventasZonaGeneralList) {
            reporte.add(new ReporteGeneralAbonado(
                    venta.getId().toString(),
                    venta.getVentaZonaGeneral().getZonaGeneral().getLocalidad(),
                    venta.getAbonado(),
                    venta.getVentaZonaGeneral().getCantidad(),
                    venta.getVendedor().getSitioVenta().getNombre(),
                    venta.getPago().getFormapago()));
        }
        return reporte;
    }
}
