package com.tickets.sec.controller;

import com.tickets.sec.dto.LoginResponse;
import com.tickets.sec.dto.Login;
import com.tickets.sec.dto.OTPRequest;
import com.tickets.sec.service.AuthService;
import com.tickets.sec.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private OTPService otpService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login credenciales) {

        if (authService.login(credenciales)) {
            return ResponseEntity.ok().body("El código OTP ha sido enviado a su correo electrónico.");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<LoginResponse> verifyOTP(@RequestBody OTPRequest request) {
        boolean isValid = otpService.validateOTP(request.getUsername(), request.getOtp());
        if (isValid) {
            LoginResponse loginResponse = authService.confirmLogin(request.getUsername());
            return ResponseEntity.ok(loginResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Encabezado de autorización inválido");
        }

        boolean sesionCerrada = authService.logout(authHeader.substring(7));

        if (sesionCerrada) {
            return ResponseEntity.ok("Sesión cerrada");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
