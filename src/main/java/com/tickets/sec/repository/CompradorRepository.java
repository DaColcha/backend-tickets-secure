package com.tickets.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.sec.model.Entity.Comprador;

public interface CompradorRepository extends JpaRepository<Comprador, Integer> {

    public Comprador findByCorreoCompradorAndNombreComprador(String correoComprador, String nombreComprador);

    public Comprador findTopByOrderByIdCompradorDesc();
}
