package com.tickets.sec.service;

import com.tickets.sec.dto.Login;
import com.tickets.sec.dto.LoginResponse;
import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.model.Entity.CredencialesSitio;
import com.tickets.sec.repository.CredencialesRepository;
import com.tickets.sec.repository.CredencialesSitioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager gestorAutenticacion;

    @Autowired
    private CredencialesRepository credencialesRepository;

    @Autowired
    private CredencialesSitioRepository credencialesSitioRepository;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(Login loginRequest) {

        try {
            gestorAutenticacion.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsuario(),
                    loginRequest.getContrasena()
            ));

            Credenciales credencialesResponse = credencialesRepository.findFirstByUsuario(loginRequest.getUsuario());

            if (credencialesResponse != null) {
                UUID id = credencialesResponse.getId();
                CredencialesSitio info = credencialesSitioRepository.findByCredencialId(id);

                Credenciales credenciales = credencialesRepository.findFirstByUsuario(loginRequest.getUsuario());
                String jwtToken = jwtService.generarToken(credenciales);

                return new LoginResponse(
                        id,
                        credencialesResponse.getRol(),
                        info.getSitioVenta().getNombre(),
                        loginRequest.getUsuario(),
                        jwtToken
                );
            } else {
                return null;
            }
        } catch (AuthenticationException e) {
            return null;
        }

    }

}
