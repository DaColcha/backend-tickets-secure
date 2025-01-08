package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentData {

    private String encryptedData;
    private BigDecimal total;
    private Boolean useCard;
    private String formaPago;

}
