package com.tickets.sec.model.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "sitio_venta")
public class SitioVenta {
    @Id
    @Column(name = "id_sitio")
    private Integer id;

    @Column(name = "usuario_sitio")
    private String nombreSitio;

    @Column(name = "acceso_sitio")
    private String contraseña;

    @Column(name = "rol_sitio")
    private String rol;

    @OneToMany(mappedBy = "sitio_venta")
    private List<Asiento> asientos;

    public SitioVenta() {
    }

    public SitioVenta(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
