package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "credenciales")
public class Credenciale {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena;

    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @OneToMany(mappedBy = "credencial")
    private Set<CredencialesSitio> credencialesSitios = new LinkedHashSet<>();

}