package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.TokenData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad TokenData
 */
public interface TokenRepository extends JpaRepository<TokenData, Long> {

    /**
     * Busca un token por su valor
     * @param token
     * @return TokenData
     */
    TokenData findByToken(String token);
}
