package com.tickets.sec.model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "asientos")
@IdClass(AsientoId.class)
public class Asiento {
    @Id
    private Integer num_asiento;

    @Id
    private String localidad;
    @Id
    private String zona;
    @Id
    private String tipo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "tipo_compra")
    private String tipo_compra;

    @ManyToOne
    @JoinColumn(name = "pago")
    private Pago pago;

    @Column(name = "plazo")
    private String plazo;

    @ManyToOne
    @JoinColumn(name = "comprador")
    private Comprador comprador;

    @ManyToOne
    @JoinColumn(name = "sitio_venta")
    private SitioVenta sitio_venta;

    public Asiento() {
    }

    public Integer getNumAsiento() {
        return num_asiento;
    }

    public void setNumAsiento(Integer numAsiento) {
        this.num_asiento = numAsiento;
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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoCompra() {
        return tipo_compra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipo_compra = tipoCompra;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
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

    public SitioVenta getSitioVenta() {
        return sitio_venta;
    }

    public void setSitioVenta(SitioVenta sitioVenta) {
        this.sitio_venta = sitioVenta;
    }

}
