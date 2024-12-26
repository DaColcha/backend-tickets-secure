package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.CredencialesSitio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CredencialesSitioRepository extends JpaRepository<CredencialesSitio, Integer> {

    @Query("SELECT c FROM CredencialesSitio c WHERE c.credencial.id = ?1")
    public CredencialesSitio findByCredencialId(UUID id);
}
