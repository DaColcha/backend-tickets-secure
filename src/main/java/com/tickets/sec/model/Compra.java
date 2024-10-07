package com.tickets.sec.model;

import java.util.List;

import com.tickets.sec.model.Entity.Comprador;
import com.tickets.sec.model.Entity.Pago;

public class Compra {

    private String localidad;
    private String zona;
    private String tipo;
    private Comprador comprador;
    private List<Integer> asientosSeleccionados;
    private String tipoCompra;
    private String sitioVenta;
    private Pago pago;
    private String plazo;

    public Compra() {
    }

    public Compra(String zona, String tipo, List<Integer> asientosSeleccionados) {
        this.zona = zona;
        this.tipo = tipo;
        this.asientosSeleccionados = asientosSeleccionados;
    }

    public Compra(String localidad, String zona, String tipo, List<Integer> asientosSeleccionados) {
        this.zona = zona;
        this.tipo = tipo;
        this.asientosSeleccionados = asientosSeleccionados;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
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

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public List<Integer> getAsientosSeleccionados() {
        return asientosSeleccionados;
    }

    public void setAsientosSeleccionados(List<Integer> asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public String getSitioVenta() {
        return sitioVenta;
    }

    public void setSitioVenta(String sitioVenta) {
        this.sitioVenta = sitioVenta;
    }

}
