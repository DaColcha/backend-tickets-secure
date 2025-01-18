package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la respuesta de una compra
 */
@Getter
@Setter
public class CompraResponse {

    private String estado;
    private String mensaje;
    private Integer idCompra;


    public CompraResponse(String estado, String mensaje, Integer id) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.idCompra = id;
    }
}
