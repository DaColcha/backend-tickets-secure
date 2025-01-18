package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la información para procesar la validación OTP
 */
@Getter
@Setter
public class OTPRequest {

    private String username;
    private String otp;
}
