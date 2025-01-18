package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CredencialesRepository extends JpaRepository<Credenciales, Integer> {

  /**
   * Método que busca las credenciales de un usuario en base a su nombre de usuario en la base de datos.
   * @see com.tickets.sec.model.Entity.Credenciales
   * 
   * @param usuario Nombre de usuario.
   * 
   * @return Credenciales Datos de credenciales.
   */
    @Query("SELECT c FROM Credenciales c WHERE c.usuario = :usuario")
    Credenciales findFirstByUsuario(String usuario);

}
