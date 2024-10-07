package com.tickets.sec.model.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @Column(name = "id_pago")
    private Integer idPago;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @OneToMany(mappedBy = "pago")
    private List<Asiento> asientos;

    public Pago() {
    }

    public Pago(Integer idPago) {
        this.idPago = idPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipo) {
        this.tipoPago = tipo;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodo) {
        this.metodoPago = metodo;
    }
}
