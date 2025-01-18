package com.tickets.sec.service;

import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.repository.CredencialesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

/**
 * Clase que gestiona el bloqueo de un usuario.
 */
@Service
@Slf4j
public class BloqueoUsuarioService {
    @Value("${login.maximo-intentos}")
    private int maximoIntentos;

    @Value("${login.minutos-bloqueo}")
    private int minutosBloqueo;

    @Autowired
    CredencialesRepository credencialesRepository;

    /**
     * Gestiona el inicio de sesión exitoso de un usuario.
     * Restablece los intentos fallidos del usuario y elimina el tiempo de bloqueo.
     * @see com.tickets.sec.model.Entity.Credenciales
     * @see com.tickets.sec.repository.CredencialesRepository
     * 
     * @param credenciales Datos del usuario.
     */
    public void gestionarInicioSesionExitoso(Credenciales credenciales) {
        if (credenciales != null) {
            credenciales.setIntentosInicioSesionFallidos(0);
            credenciales.setTiempoBloqueo(null);
            credencialesRepository.save(credenciales);
        }
    }

    /**
     * Gestiona el inicio de sesión fallido de un usuario.
     * Incrementa el número de intentos fallidos del usuario y, si supera el máximo de intentos, bloquea al usuario durante
     * el tiempo especificado en minutos en el archivo de propiedades.
     * @see com.tickets.sec.model.Entity.Credenciales
     * @see com.tickets.sec.repository.CredencialesRepository
     * 
     * @param credenciales Datos del usuario.
     */
    public void gestionarInicioSesionFallido(Credenciales credenciales) {
        if (credenciales != null) {
            int intentosFallidos = credenciales.getIntentosInicioSesionFallidos() + 1;
            credenciales.setIntentosInicioSesionFallidos(intentosFallidos);

            if (intentosFallidos >= maximoIntentos) {
                credenciales.setTiempoBloqueo(Instant.now());
                log.error("Usuario " + credenciales.getUsuario() + " ha agotado los intentos de inicio de sesión. Bloqueado por " + minutosBloqueo + " minutos.");
            }

            credencialesRepository.save(credenciales);
        }
    }

    /**
     * Verifica si un usuario está bloqueado.
     * @see com.tickets.sec.model.Entity.Credenciales
     * 
     * En caso de que el usuario esté bloqueado, verifica si ha pasado el tiempo de bloqueo.
     * Si ha pasado, restablece los intentos fallidos y elimina el tiempo de bloqueo.
     * @see com.tickets.sec.repository.CredencialesRepository
     * 
     * @param credenciales Datos del usuario.
     * @return boolean True si el usuario está bloqueado, false en caso contrario.
     */
    public boolean verificarBloqueo(Credenciales credenciales) {
        if (credenciales != null && credenciales.getTiempoBloqueo() != null) {
            Instant tiempoBloqueo = credenciales.getTiempoBloqueo();
            if (tiempoBloqueo.plus(Duration.ofMinutes(minutosBloqueo)).isAfter(Instant.now())) {
                log.info("Intento de inicio de sesión de usuario bloqueado: " + credenciales.getUsuario());
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
