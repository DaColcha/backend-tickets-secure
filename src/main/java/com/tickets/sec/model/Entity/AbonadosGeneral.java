package com.tickets.sec.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "abonados_general")
public class AbonadosGeneral {
    @Id
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "zona")
    private String zona;

    @ManyToOne
    @JoinColumn(name = "comprador")
    private Comprador comprador;

    @Column(name = "cantidad_boletos")
    private Integer cantidadBoletos;

    @ManyToOne
    @JoinColumn(name = "sitio_venta")
    private SitioVenta sitioVenta;

    @ManyToOne
    @JoinColumn(name = "pago")
    private Pago pago;

    @Column(name = "plazo")
    private String plazo;

    public AbonadosGeneral() {
    }

    public AbonadosGeneral(Integer idCompra, String zona, Comprador comprador,
            Integer cantidadBoletos, SitioVenta sitioVenta, Pago pago, String plazo) {
        this.idCompra = idCompra;
        this.zona = zona;
        this.comprador = comprador;
        this.cantidadBoletos = cantidadBoletos;
        this.sitioVenta = sitioVenta;
        this.pago = pago;
        this.plazo = plazo;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
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

    public SitioVenta getSitioVenta() {
        return sitioVenta;
    }

    public void setSitioVenta(SitioVenta sitioVenta) {
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
}