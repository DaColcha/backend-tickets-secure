package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByUsername(String username);
    void deleteByUsername(String username);
}
