package com.tickets.sec.model;

import java.util.ArrayList;
import java.util.List;

import com.tickets.sec.model.Entity.AbonadosGeneral;
import com.tickets.sec.model.Entity.Comprador;
import com.tickets.sec.model.Entity.Pago;

public class ReporteGeneralAbonado {

    private String idCompra;
    private String zona;
    private Comprador comprador;
    private Integer cantidadBoletos;
    private String sitioVenta;
    private Pago pago;
    private String plazo;

    public ReporteGeneralAbonado() {
    }

    public ReporteGeneralAbonado(String idCompra, String zona, Comprador comprador, Integer cantidadBoletos,
            String sitioVenta,
            Pago pago, String plazo) {
        this.idCompra = idCompra;
        this.zona = zona;
        this.comprador = comprador;
        this.cantidadBoletos = cantidadBoletos;
        this.sitioVenta = sitioVenta;
        this.pago = pago;
        this.plazo = plazo;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Integer getCantidadBoletos() {
        return cantidadBoletos;
    }

    public void setCantidadBoletos(Integer cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }

    public String getSitioVenta() {
        return sitioVenta;
    }

    public void setSitioVenta(String sitioVenta) {
        this.sitioVenta = sitioVenta;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public List<ReporteGeneralAbonado> createReport(List<AbonadosGeneral> abonados) {
        List<ReporteGeneralAbonado> reporte = new ArrayList<>();
        for (AbonadosGeneral abonado : abonados) {
            reporte.add(new ReporteGeneralAbonado(
                    abonado.getIdCompra().toString(),
                    abonado.getZona(),
                    abonado.getComprador(),
                    abonado.getCantidadBoletos(),
                    abonado.getSitioVenta().getNombreSitio(),
                    abonado.getPago(),
                    abonado.getPlazo()));
        }
        return reporte;
    }
}
