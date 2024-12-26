package com.tickets.sec.dto;

import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.model.Entity.FormaPago;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Compra {

    private String localidad;
    private String zona;
    private String tipo;
    private Abonado comprador;
    private List<Integer> asientosSeleccionados;
    private String tipoCompra;
    private Integer vendedor;
    private FormaPago formaPago;
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


}
