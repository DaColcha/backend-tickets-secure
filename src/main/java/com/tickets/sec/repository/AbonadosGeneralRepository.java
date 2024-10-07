package com.tickets.sec.repository;

import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tickets.sec.model.Entity.AbonadosGeneral;

import jakarta.transaction.Transactional;

public interface AbonadosGeneralRepository extends JpaRepository<AbonadosGeneral, Integer> {

    public AbonadosGeneral findTopByOrderByIdCompraDesc();

    public AbonadosGeneral findByIdCompra(Integer idCompra);

    @Transactional
    @Modifying
    @Query(value = "TRUNCATE TABLE abonados_general", nativeQuery = true)
    public void cleanAbonadosGeneral();

    @Query("select sum(a.cantidadBoletos) from AbonadosGeneral a where zona = :zona")
    public Integer cantidadBoletosAbonadosByZona(@Name("zona") String zona);

    public void deleteByIdCompra(Integer idCompra);
}
