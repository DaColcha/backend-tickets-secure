package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Clase que representa la información para procesar un pago
 */
@Getter
@Setter
public class PaymentData {

    private String encryptedData;
    private BigDecimal total;
    private Boolean useCard;
    private String formaPago;

}
