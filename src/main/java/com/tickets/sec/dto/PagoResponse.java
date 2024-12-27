package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PagoResponse {

    private UUID id;
    private String comprobante;
    private String estado;
    private String mensaje;
    private BigDecimal monto;
    private LocalDateTime timestamp;

    public PagoResponse() {
    }

    public PagoResponse(UUID ID, String comprobante, String estado, String mensaje, BigDecimal monto, LocalDateTime timestamp) {
        this.id = ID;
        this.comprobante = comprobante;
        this.estado = estado;
        this.mensaje = mensaje;
        this.monto = monto;
        this.timestamp = timestamp;
    }
}
