package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CredencialesRepository extends JpaRepository<Credenciales, Integer> {

    @Query("SELECT c.rol FROM Credenciales c WHERE c.usuario = :usuario AND c.contrasena = :contrasena")
    String rolAutenticado(String usuario, String contrasena);
}
