package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CredencialesRepository extends JpaRepository<Credenciales, Integer> {

    @Query("SELECT c FROM Credenciales c WHERE c.usuario = :usuario")
    Credenciales findFirstByUsuario(String usuario);

}
