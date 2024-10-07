package com.tickets.sec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tickets.sec.model.Entity.Asiento;
import com.tickets.sec.model.Entity.Comprador;
import com.tickets.sec.model.Entity.Pago;

public class ReporteComprado {

    private Integer id;

    private String zona;
    private String tipo;
    private List<Integer> asientos;
    private Comprador comprador;
    private String tipoCompra;
    private String sitioVenta;
    private Pago pago;
    private String plazo;

    public ReporteComprado() {
    }

    public ReporteComprado(Integer id, String zona, String tipo, List<Integer> asientos, Comprador comprador,
            String tipoCompra,
            String sitioVenta, Pago pago, String plazo) {
        this.id = id;
        this.zona = zona;
        this.tipo = tipo;
        this.asientos = asientos;
        this.comprador = comprador;
        this.tipoCompra = tipoCompra;
        this.sitioVenta = sitioVenta;
        this.pago = pago;
        this.plazo = plazo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Integer> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Integer> asientos) {
        this.asientos = asientos;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipoCompra = tipoCompra;
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

    public List<ReporteComprado> createReport(List<Asiento> asientos) {
        List<ReporteComprado> vendidos = new ArrayList<>();

        List<Comprador> compradores = asientos.stream().map(a -> a.getComprador()).distinct().toList();

        Integer count = 1;
        for (Comprador c : compradores) {
            List<Asiento> asientosComprados = asientos.stream().filter(a -> a.getComprador().equals(c)).toList();

            List<List<Asiento>> asientosList = asientosComprados.stream()
                    .collect(Collectors.groupingBy(
                            asiento -> asiento.getComprador() + "-" +
                                    asiento.getLocalidad() + "-" +
                                    asiento.getZona() + "-" +
                                    asiento.getTipo()))
                    .values().stream().toList();

            for (List<Asiento> asientoList : asientosList) {
                List<Integer> numAsientosComprados = asientoList.stream().map(a -> a.getNumAsiento()).toList();
                ReporteComprado reporte = new ReporteComprado(
                        count,
                        asientoList.get(0).getZona(),
                        asientoList.get(0).getTipo(),
                        numAsientosComprados, c,
                        asientoList.get(0).getTipoCompra(),
                        asientoList.get(0).getSitioVenta().getNombreSitio(),
                        asientoList.get(0).getPago(),
                        asientoList.get(0).getPlazo());
                vendidos.add(reporte);
                count++;
            }
        }

        return vendidos;
    }

}
