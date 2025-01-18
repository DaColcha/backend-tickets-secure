package com.tickets.sec.dto;

import com.tickets.sec.model.Entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa las
 */
@Getter
@Setter
public class ReporteGeneralAbonado {


    private String idCompra;
    private String zona;
    private Abonado comprador;
    private Integer cantidadBoletos;
    private String sitioVenta;
    private FormaPago pago;

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


}
