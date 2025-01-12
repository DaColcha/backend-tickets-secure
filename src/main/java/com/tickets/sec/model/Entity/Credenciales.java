package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "credenciales")
public class Credenciales {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena;

    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "credencial")
    private Set<CredencialesSitio> credencialesSitios = new LinkedHashSet<>();

    @NotNull
    @ColumnDefault("0")
    @Column(name = "intentosiniciosesionfallidos", nullable = false)
    private Integer intentosInicioSesionFallidos;

    @Column(name = "tiempobloqueo")
    private Instant tiempoBloqueo;

}