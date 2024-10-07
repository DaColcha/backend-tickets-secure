package com.tickets.sec.model.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "compradores")
public class Comprador {

    @Id
    @Column(name = "id_comprador")
    private Integer idComprador;

    @Column(name = "correo_comprador")
    private String correoComprador;

    @Column(name = "nombre_comprador")
    private String nombreComprador;

    @Column(name = "telefono_comprador")
    private String telefonoComprador;

    @OneToMany(mappedBy = "comprador")
    private List<Asiento> asientos;

    public Comprador() {
    }

    public Comprador(String correoComprador, String nombreComprador, String telefonoComprador) {
        this.correoComprador = correoComprador;
        this.nombreComprador = nombreComprador;
        this.telefonoComprador = telefonoComprador;
    }

    public Comprador(Integer idComprador, String correoComprador, String nombreComprador, String telefonoComprador) {
        this.idComprador = idComprador;
        this.correoComprador = correoComprador;
        this.nombreComprador = nombreComprador;
        this.telefonoComprador = telefonoComprador;
    }

    public Integer getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Integer idComprador) {
        this.idComprador = idComprador;
    }

    public String getCorreoComprador() {
        return correoComprador;
    }

    public void setCorreoComprador(String correoComprador) {
        this.correoComprador = correoComprador;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getTelefonoComprador() {
        return telefonoComprador;
    }

    public void setTelefonoComprador(String telefonoComprador) {
        this.telefonoComprador = telefonoComprador;
    }

}
