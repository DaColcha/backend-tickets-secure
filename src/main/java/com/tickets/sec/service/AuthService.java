package com.tickets.sec.service;

import com.tickets.sec.dto.Login;
import com.tickets.sec.dto.LoginResponse;
import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.model.Entity.CredencialesSitio;
import com.tickets.sec.model.Entity.TokensJwtExpirados;
import com.tickets.sec.repository.CredencialesRepository;
import com.tickets.sec.repository.CredencialesSitioRepository;
import com.tickets.sec.repository.TokensJwtExpiradosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private TokensJwtExpiradosRepository tokensJwtExpiradosRepository;

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

    public boolean logout(String token) {

        String usuario = jwtService.extraerUsuario(token);

        if (usuario != null) {
            Credenciales credenciales = credencialesRepository.findFirstByUsuario(usuario);
            TokensJwtExpirados tokenExpirado = new TokensJwtExpirados();
            tokenExpirado.setToken(token);
            tokenExpirado.setCredenciales(credenciales);
            tokensJwtExpiradosRepository.save(tokenExpirado);
            SecurityContextHolder.clearContext();
            return true;
        }

        return false;

    }

}
