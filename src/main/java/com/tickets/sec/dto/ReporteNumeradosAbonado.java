package com.tickets.sec.dto;

import com.tickets.sec.model.Entity.Abonado;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReporteNumeradosAbonado {

    private Integer id;

    private String zona;
    private String tipo;
    private List<Integer> asientos;
    private Abonado comprador;
    private String tipoCompra;
    private String sitioVenta;
    private String pago;

    public ReporteNumeradosAbonado(Integer id, String zona, String tipo, List<Integer> asientos, Abonado comprador,
                                   String tipoCompra,
                                   String sitioVenta, String pago) {
        this.id = id;
        this.zona = zona;
        this.tipo = tipo;
        this.asientos = asientos;
        this.comprador = comprador;
        this.tipoCompra = tipoCompra;
        this.sitioVenta = sitioVenta;
        this.pago = pago;
    }

}
