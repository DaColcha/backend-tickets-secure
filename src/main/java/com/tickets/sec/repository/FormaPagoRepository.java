package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FormaPagoRepository extends JpaRepository<FormaPago, Integer> {
}
