package com.tickets.sec.dto;

import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.model.Entity.AsientosNumerado;
import com.tickets.sec.model.Entity.Pago;

import java.util.ArrayList;
import java.util.List;

public class ReporteComprado {

    private Integer id;

    private String zona;
    private String tipo;
    private List<Integer> asientos;
    private Abonado comprador;
    private String tipoCompra;
    private String sitioVenta;
    private Pago pago;
    private String plazo;

    public ReporteComprado() {
    }

    public ReporteComprado(Integer id, String zona, String tipo, List<Integer> asientos, Abonado comprador,
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

    public Abonado getComprador() {
        return comprador;
    }

    public void setComprador(Abonado comprador) {
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

    public List<ReporteComprado> createReport(List<AsientosNumerado> asientos) {
        List<ReporteComprado> vendidos = new ArrayList<>();

        //TODO: Implementar el metodo createReport

//        List<Abonado> compradores = asientos.stream().map(a -> a.getComprador()).distinct().toList();
//
//        Integer count = 1;
//        for (Comprador c : compradores) {
//            List<Asiento> asientosComprados = asientos.stream().filter(a -> a.getComprador().equals(c)).toList();
//
//            List<List<Asiento>> asientosList = asientosComprados.stream()
//                    .collect(Collectors.groupingBy(
//                            asiento -> asiento.getComprador() + "-" +
//                                    asiento.getLocalidad() + "-" +
//                                    asiento.getZona() + "-" +
//                                    asiento.getTipo()))
//                    .values().stream().toList();
//
//            for (List<Asiento> asientoList : asientosList) {
//                List<Integer> numAsientosComprados = asientoList.stream().map(a -> a.getNumAsiento()).toList();
//                ReporteComprado reporte = new ReporteComprado(
//                        count,
//                        asientoList.get(0).getZona(),
//                        asientoList.get(0).getTipo(),
//                        numAsientosComprados, c,
//                        asientoList.get(0).getTipoCompra(),
//                        asientoList.get(0).getSitioVenta().getNombreSitio(),
//                        asientoList.get(0).getPago(),
//                        asientoList.get(0).getPlazo());
//                vendidos.add(reporte);
//                count++;
//            }
//        }

        return vendidos;
    }

}
