package com.tickets.sec.controller;

import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.repository.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CredencialesRepository credencialesRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credenciales credenciales) {
        String rol = credencialesRepository.rolAutenticado(credenciales.getUsuario(), credenciales.getContrasena());
        if (rol != null) {
            return ResponseEntity.ok(rol);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
