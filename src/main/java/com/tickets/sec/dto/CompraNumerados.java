package com.tickets.sec.dto;

import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.model.Entity.FormaPago;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CompraNumerados {

    private String localidad;
    private String zona;
    private String tipo;
    private Abonado comprador;
    private List<Integer> asientosSeleccionados;
    private String tipoCompra;
    private String vendedor;
    private String formaPago;
    private UUID idPago;

    public CompraNumerados() {
    }

    public CompraNumerados(String zona, String tipo, List<Integer> asientosSeleccionados) {
        this.zona = zona;
        this.tipo = tipo;
        this.asientosSeleccionados = asientosSeleccionados;
    }

    public CompraNumerados(String localidad, String zona, String tipo, List<Integer> asientosSeleccionados) {
        this.zona = zona;
        this.tipo = tipo;
        this.asientosSeleccionados = asientosSeleccionados;
    }


}
