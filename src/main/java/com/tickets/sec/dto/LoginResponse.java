package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LoginResponse {


    private UUID id;
    private String rol;
    private String sitio;
    private String usuario;

    public LoginResponse() {
    }

    public LoginResponse(UUID id, String rol, String sitio, String usuario) {
        this.rol = rol;
        this.id = id;
        this.sitio = sitio;
        this.usuario = usuario;
    }

}
