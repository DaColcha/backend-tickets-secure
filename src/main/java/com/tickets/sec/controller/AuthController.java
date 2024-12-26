package com.tickets.sec.controller;

import com.tickets.sec.dto.LoginResponse;
import com.tickets.sec.model.Entity.CredencialesSitio;
import com.tickets.sec.dto.Login;
import com.tickets.sec.repository.CredencialesRepository;
import com.tickets.sec.repository.CredencialesSitioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CredencialesRepository credencialesRepository;
    @Autowired
    private CredencialesSitioRepository credencialesSitioRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Login credenciales) {
        List<Object[]> response = credencialesRepository.rolAutenticado(credenciales.getUsuario(), credenciales.getContrasena());

        if (response != null) {

            UUID id = (UUID) response.get(0)[1];
            CredencialesSitio info = credencialesSitioRepository.findByCredencialId(id);

            LoginResponse loginResponse = new LoginResponse(
                    id,
                    (String) response.get(0)[0],
                    info.getSitioVenta().getNombre(),
                    credenciales.getUsuario()
            );

            return ResponseEntity.ok().body(loginResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
