package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.VentasAsientosNumerado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasAsientosNumeradosRepository extends JpaRepository<VentasAsientosNumerado, Integer> {
}
