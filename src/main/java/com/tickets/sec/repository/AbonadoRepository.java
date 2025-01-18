package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.Abonado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que define los métodos para el acceso a la base de datos de los abonados.
 */
public interface AbonadoRepository extends JpaRepository<Abonado, Integer> {

    /**
     * Método que busca un abonado en base a su cédula.
     * @param cedula
     * @return Abonado Datos del abonado.
     */
    public Abonado findByCedula(String cedula);
}
