package com.tickets.sec.dto;

import com.tickets.sec.model.Entity.Abonado;
import com.tickets.sec.model.Entity.FormaPago;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraGeneral {

    private String zona;
    private Integer cantidad;
    private Abonado comprador;
    private String tipoCompra;
    private String vendedor;
    private String formaPago;

    private String token;
}
