package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para la entidad OTP
 */
public interface OtpRepository extends JpaRepository<OTP, Long> {

    /**
     * Busca el ultimo OTP generado para un usuario
     * @param username
     * @return OTP
     */
    Optional<OTP> findTopByUsernameOrderByIdDesc(String username);

    /**
     * Elimina todos los OTP existentes de un usuario
     * @param username
     */
    void deleteByUsername(String username);
}
