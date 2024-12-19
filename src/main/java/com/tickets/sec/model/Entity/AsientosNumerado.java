package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "asientos_numerados")
public class AsientosNumerado {

    @Id
    @ColumnDefault("nextval('ventas_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    private String localidad;
    private String zona;
    private String tipo;
    private Integer numero;
    private String estado;

}