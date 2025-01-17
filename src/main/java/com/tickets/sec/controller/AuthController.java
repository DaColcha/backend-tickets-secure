package com.tickets.sec.controller;

import com.tickets.sec.dto.LoginResponse;
import com.tickets.sec.dto.Login;
import com.tickets.sec.dto.OTPRequest;
import com.tickets.sec.service.AuthService;
import com.tickets.sec.service.OTPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private OTPService otpService;

    /**
     * Método que registra la ruta "/login" y recibe las credenciales de un usuario y genera un código OTP.
     * @see com.tickets.sec.service.AuthService
     * @see com.tickets.sec.service.OTPService
     * 
     * @param credenciales Datos de inicio de sesión.
     * 
     * @return org.springframework.http.ResponseEntity<String> Respuesta de inicio de sesión.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login credenciales) {
        String loginResponse = authService.login(credenciales);

        if (loginResponse.equals(AuthService.LOGIN_EXITOSO)) {
            log.info("Se ha generado un nuevo código OTP.");
            return ResponseEntity.ok().body("El código OTP ha sido enviado a su correo electrónico.");
        } else {
            log.warn(loginResponse);
            return ResponseEntity.badRequest().body(loginResponse);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<LoginResponse> verifyOTP(@RequestBody OTPRequest request) {
        boolean isValid = otpService.validateOTP(request.getUsername(), request.getOtp());
        if (isValid) {
            log.info("Código OTP válido. Iniciando sesión.");
            LoginResponse loginResponse = authService.confirmLogin(request.getUsername());
            log.info("Inicio de sesión exitoso.");
            return ResponseEntity.ok(loginResponse);
        }
        log.warn("Intento de inicio de sesión fallido. Código OTP inválido.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    /**
     * Método que registra la ruta "/logout" y cierra la sesión de un usuario, siempre y cuando tenga un token JWT válido.
     * @see com.tickets.sec.service.AuthService
     * 
     * @param authHeader Encabezado de autorización.
     * 
     * @return org.springframework.http.ResponseEntity<?> Respuesta de cierre de sesión.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Encabezado de autorización inválido. No se puede cerrar sesión.");
            return ResponseEntity.badRequest().body("Encabezado de autorización inválido");
        }

        boolean sesionCerrada = authService.logout(authHeader.substring(7));

        if (sesionCerrada) {
            log.info("Sesión cerrada.");
            return ResponseEntity.ok("Sesión cerrada");
        } else {
            log.warn("No se pudo cerrar la sesión.");
            return ResponseEntity.badRequest().build();
        }

    }

}
