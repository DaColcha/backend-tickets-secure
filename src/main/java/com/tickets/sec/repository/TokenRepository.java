package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.TokenData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TokenRepository extends JpaRepository<TokenData, Long> {
    TokenData findByToken(String token);
}
