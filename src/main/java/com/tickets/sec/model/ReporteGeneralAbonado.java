package com.tickets.sec.model;

import com.tickets.sec.model.Entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReporteGeneralAbonado {


    private String idCompra;
    private String zona;
    private Abonado comprador;
    private Integer cantidadBoletos;
    private String sitioVenta;
    private FormaPago pago;

    public ReporteGeneralAbonado() {
    }

    public ReporteGeneralAbonado(String idCompra, String zona, Abonado comprador, Integer cantidadBoletos,
            String sitioVenta,
            FormaPago pago) {
        this.idCompra = idCompra;
        this.zona = zona;
        this.comprador = comprador;
        this.cantidadBoletos = cantidadBoletos;
        this.sitioVenta = sitioVenta;
        this.pago = pago;
    }

    public List<ReporteGeneralAbonado> createReport(List<Venta> ventasZonaGeneralList) {
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
