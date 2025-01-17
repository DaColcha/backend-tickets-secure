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

    @Autowired
    private OTPService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BloqueoUsuarioService bloqueoUsuarioService;

    public static final String LOGIN_EXITOSO = "Inicio de sesión exitoso";
    public static final String LOGIN_FALLIDO_CREDENCIALES = "Inicio de sesión fallido, credenciales inválidas.";
    public static final String LOGIN_FALLIDO_BLOQUEO = "Inicio de sesión fallido, cuenta bloqueada temporalmente.";

    /**
     * Método que gestiona el inicio de sesión de un usuario y lo autentica en el backend.
     * @see org.springframework.security.authentication.AuthenticationManager
     * @see com.tickets.sec.repository.CredencialesRepository
     * 
     * Si el usuario está bloqueado, devuelve un mensaje de notificación de bloqueo al usuario.
     * @see  com.tickets.sec.service.BloqueoUsuarioService
     * 
     * Si las credenciales son incorrectas, devuelve un mensaje de notificación de credenciales inválidas.
     * 
     * Si las credenciales son correctas, autentica al usuario y envía un correo electrónico con un código OTP.
     * @see com.tickets.sec.service.OTPService
     * @see com.tickets.sec.service.EmailService
     * 
     * @param loginRequest Datos de inicio de sesión.
     * 
     * @return java.lang.String Mensaje de respuesta.
     */
    public String login(Login loginRequest) {
        Credenciales credencialesResponse = credencialesRepository.findFirstByUsuario(loginRequest.getUsuario());

        if ((credencialesResponse != null)) {
            if (!bloqueoUsuarioService.verificarBloqueo(credencialesResponse)) {
                try {
                    gestorAutenticacion.authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsuario(),
                            loginRequest.getContrasena()
                    ));

                    bloqueoUsuarioService.gestionarInicioSesionExitoso(credencialesResponse);

                    String otp = otpService.generateOTP(credencialesResponse.getUsuario());
                    emailService.sendEmail(credencialesResponse.getEmail(), otp);

                    return LOGIN_EXITOSO;
                } catch (AuthenticationException e) {
                    bloqueoUsuarioService.gestionarInicioSesionFallido(credencialesResponse);

                    return LOGIN_FALLIDO_CREDENCIALES;
                }
            } else {
                return LOGIN_FALLIDO_BLOQUEO;
            }
        } else {
            return LOGIN_FALLIDO_CREDENCIALES;
        }
    }

    public LoginResponse confirmLogin(String usuario) {
        Credenciales credenciales = credencialesRepository.findFirstByUsuario(usuario);

        CredencialesSitio info = credencialesSitioRepository.findByCredencialId(credenciales.getId());

        String jwtToken = jwtService.generarToken(credenciales);

        return new LoginResponse(
                credenciales.getId(),
                credenciales.getRol(),
                info.getSitioVenta().getNombre(),
                usuario,
                jwtToken
        );
    }

    /**
     * Método que cierra la sesión de un usuario.
     * @see com.tickets.sec.service.JwtService#extraerUsuario(String)
     * @see com.tickets.sec.repository.CredencialesRepository
     * 
     * Además, almacena el token del usuario en la tabla de tokens expirados.
     * @see com.tickets.sec.repository.TokensJwtExpiradosRepository
     * 
     * @param token Token JWT.
     * 
     * @return boolean True si el cierre de sesión ha sido exitoso, false en caso contrario.
     */
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
