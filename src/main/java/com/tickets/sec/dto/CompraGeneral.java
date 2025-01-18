package com.tickets.sec.dto;

import com.tickets.sec.model.Entity.Abonado;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Clase que representa la información necesaria para realizar una compra general.
 */
@Getter
@Setter
public class CompraGeneral {

    private String zona;
    private Integer cantidad;
    private Abonado comprador;
    private String tipoCompra;
    private String vendedor;
    private String formaPago;
    private UUID idPago;
}
