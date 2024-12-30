package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.TokensJwtExpirados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokensJwtExpiradosRepository extends JpaRepository<TokensJwtExpirados, String> {

    @Query("SELECT t FROM TokensJwtExpirados t WHERE t.token = :token")
    TokensJwtExpirados findByToken(String token);

}
