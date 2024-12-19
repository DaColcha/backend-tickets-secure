package com.tickets.sec.controller;

import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.model.Entity.CredencialesSitio;
import com.tickets.sec.repository.CredencialesRepository;
import com.tickets.sec.repository.CredencialesSitioRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CredencialesRepository credencialesRepository;
    @Autowired
    private CredencialesSitioRepository credencialesSitioRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credenciales credenciales) {
        Pair<String, Integer> response = credencialesRepository.rolAutenticado(credenciales.getUsuario(), credenciales.getContrasena());

        if (response != null) {
            CredencialesSitio info = credencialesSitioRepository.findByCredencialId(response.b);

            return ResponseEntity.ok(Map.of(
                "rol", response.a,
                "id", response.b,
                "sitio", info.getSitioVenta().getNombre()
            ).toString());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
