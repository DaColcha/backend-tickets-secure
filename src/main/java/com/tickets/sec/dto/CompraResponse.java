package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraResponse {

    private String estado;
    private String mensaje;

    public CompraResponse(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }
}
