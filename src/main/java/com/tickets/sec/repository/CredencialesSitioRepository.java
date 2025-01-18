package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.CredencialesSitio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

/**
 * Interface que permite la interacción con la base de datos para la entidad CredencialesSitio
 */
public interface CredencialesSitioRepository extends JpaRepository<CredencialesSitio, Integer> {

    /**
     * Método que permite buscar una credencial por su id
     * @param id
     * @return
     */
    @Query("SELECT c FROM CredencialesSitio c WHERE c.credencial.id = ?1")
    public CredencialesSitio findByCredencialId(UUID id);

    /**
     * Método que permite buscar una credencial por su usuario
     * @param user
     * @return
     */
    @Query("SELECT c FROM CredencialesSitio c WHERE c.credencial.usuario= ?1")
    public CredencialesSitio findByUsuario(String user);
}
