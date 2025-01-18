package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.VentasAsientosNumerado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad VentasAsientosNumerado
 */
public interface VentasAsientosNumeradosRepository extends JpaRepository<VentasAsientosNumerado, Integer> {
}
