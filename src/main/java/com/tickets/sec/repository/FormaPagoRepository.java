package com.tickets.sec.repository;

import com.tickets.sec.model.Entity.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio para la entidad FormaPago
 */
public interface FormaPagoRepository extends JpaRepository<FormaPago, Integer> {

    /**
     * Busca una forma de pago por su nombre
     * @param formapago
     * @return
     */
    @Query("SELECT f FROM FormaPago f WHERE f.formaPago= ?1")
    FormaPago findByFormaPago(String formapago);
}
