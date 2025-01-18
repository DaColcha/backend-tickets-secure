package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la información de un abonado
 */
@Getter
@Setter
public class AbonadoResponse {

    private String cedula;
    private String nombre;
    private String correo;
    private String telefono;

    public AbonadoResponse(String cedula, String nombre, String correo, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
}
