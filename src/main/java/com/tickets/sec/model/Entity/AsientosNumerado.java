package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asientos_numerados")
@IdClass(AsientosNumeradoId.class)
public class AsientosNumerado {
    @Id
    private String localidad;

    @Id
    private String zona;

    @Id
    private String tipo;

    @Id
    private Integer numero;

    private String estado;

}