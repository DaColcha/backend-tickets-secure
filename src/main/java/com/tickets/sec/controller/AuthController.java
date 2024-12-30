package com.tickets.sec.controller;

import com.tickets.sec.dto.LoginResponse;
import com.tickets.sec.dto.Login;
import com.tickets.sec.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Login credenciales) {
        LoginResponse loginResponse = authService.login(credenciales);

        if (loginResponse != null) {
            return ResponseEntity.ok().body(loginResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
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
