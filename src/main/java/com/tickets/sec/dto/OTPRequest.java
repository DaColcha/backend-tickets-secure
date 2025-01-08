package com.tickets.sec.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPRequest {

    private String username;
    private String otp;
}
