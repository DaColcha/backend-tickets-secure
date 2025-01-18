package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.TokensJwtExpirados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokensJwtExpiradosRepository extends JpaRepository<TokensJwtExpirados, String> {

    /**
     * Método que busca un token JWT en la tabla de tokens expirados en la base de datos.
     * @see com.tickets.sec.model.Entity.TokensJwtExpirados
     * 
     * @param token Token JWT.
     * 
     * @return TokensJwtExpirados Datos de token expirado.
     */
    @Query("SELECT t FROM TokensJwtExpirados t WHERE t.token = :token")
    TokensJwtExpirados findByToken(String token);

}
