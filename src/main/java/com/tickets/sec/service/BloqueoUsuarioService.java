package com.tickets.sec.service;

import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.repository.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class BloqueoUsuarioService {
    @Value("${login.maximo-intentos}")
    private int maximoIntentos;

    @Value("${login.minutos-bloqueo}")
    private int minutosBloqueo;

    @Autowired
    CredencialesRepository credencialesRepository;

    public void gestionarInicioSesionExitoso(Credenciales credenciales) {
        if (credenciales != null) {
            credenciales.setIntentosInicioSesionFallidos(0);
            credenciales.setTiempoBloqueo(null);
            credencialesRepository.save(credenciales);
        }
    }

    public void gestionarInicioSesionFallido(Credenciales credenciales) {
        if (credenciales != null) {
            int intentosFallidos = credenciales.getIntentosInicioSesionFallidos() + 1;
            credenciales.setIntentosInicioSesionFallidos(intentosFallidos);

            if (intentosFallidos >= maximoIntentos) {
                credenciales.setTiempoBloqueo(Instant.now());
            }

            credencialesRepository.save(credenciales);
        }
    }

    public boolean verificarBloqueo(Credenciales credenciales) {
        if (credenciales != null && credenciales.getTiempoBloqueo() != null) {
            Instant tiempoBloqueo = credenciales.getTiempoBloqueo();
            if (tiempoBloqueo.plus(Duration.ofMinutes(minutosBloqueo)).isAfter(Instant.now())) {
                return true;
            } else {
                credenciales.setTiempoBloqueo(null);
                credenciales.setIntentosInicioSesionFallidos(0);
                credencialesRepository.save(credenciales);
            }
        }
        return false;
    }
}
