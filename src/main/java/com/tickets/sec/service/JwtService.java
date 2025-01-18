package com.tickets.sec.service;

import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.model.Entity.TokensJwtExpirados;
import com.tickets.sec.repository.TokensJwtExpiradosRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.llave-secreta}")
    private String llaveSecreta;

    @Value("${jwt.expiracion-token}")
    private long expiracionToken;

    @Autowired
    TokensJwtExpiradosRepository tokensJwtExpiradosRepository;

    @Autowired
    EncriptacionService encriptacionService;

    /**
     * Genera un token JWT a partir del nombre de usuario, la fecha de creación del token , la fecha de expiración y la llave secreta para firmarlo.
     * @see com.tickets.sec.model.Entity.Credenciales
     * @see com.tickets.sec.service.JwtService#generarLlaveSecreta()
     * 
     * Además, encripta el cuerpo del token para mayor seguridad.
     * @see com.tickets.sec.service.EncriptacionService#encriptar(String)
     * 
     * @param credenciales Datos del usuario.
     * 
     * @return java.lang.String Token JWT.
     */
    public String generarToken(final Credenciales credenciales) {
        String usuarioEncriptado = encriptacionService.encriptar(credenciales.getUsuario());
        return Jwts.builder()
                .setId(credenciales.getId().toString())
                .setSubject(usuarioEncriptado)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiracionToken))
                .signWith(this.generarLlaveSecreta())
                .compact();
    }

    /**
     * Genera una llave secreta a partir de la llave secreta codificada en base 64 almacenada en el archivo de propiedades.
     * @return javax.crypto.SecretKey Llave secreta.
     */
    private SecretKey generarLlaveSecreta() {
        byte[] bytesLlave = Decoders.BASE64.decode(llaveSecreta);
        return Keys.hmacShaKeyFor(bytesLlave);
    }

    /**
     * Extrae el nombre de usuario del token JWT.
     * @see com.tickets.sec.service.JwtService#generarLlaveSecreta()
     * @see com.tickets.sec.service.EncriptacionService#desencriptar(String)
     * 
     * @param token Token JWT.
     * 
     * @return java.lang.String Nombre de usuario.
     */
    public String extraerUsuario(String token) {
        return encriptacionService.desencriptar(Jwts.parserBuilder()
                .setSigningKey(generarLlaveSecreta())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    /**
     * Extrae la fecha de expiración del token JWT.
     * @see com.tickets.sec.service.JwtService#generarLlaveSecreta()
     * 
     * @param token Token JWT.
     * 
     * @return java.util.Date Fecha de expiración.
     */
    private Date extraerExpiracion(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generarLlaveSecreta())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    /**
     * Verifica si el token JWT ha expirado.
     * @see com.tickets.sec.service.JwtService#extraerExpiracion(String)
     * 
     * @param token Token JWT.
     * 
     * @return boolean True si el token ha expirado, false en caso contrario.
     */
    private boolean verificarTiempoExpiracionToken(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    /**
     * Verifica si el token JWT ha sido almacenado en la tabla de tokens expirados en la base de datos.
     * @see com.tickets.sec.repository.TokensJwtExpiradosRepository
     * 
     * @param token Token JWT.
     * 
     * @return boolean True si el token ha sido almacenado, false en caso contrario.
     */
    private boolean verificarTokenExpirado(String token) {
        return (tokensJwtExpiradosRepository.findByToken(token) != null);
    }

    /**
     * Verifica la validez de un token JWT.
     * @see com.tickets.sec.service.JwtService#extraerUsuario(String)
     * @see com.tickets.sec.service.JwtService#verificarTiempoExpiracionToken(String)
     * 
     * Compara la información del token con los datos del usuario recibido.
     * @see com.tickets.sec.model.Entity.Credenciales
     * 
     * Si el token ha expirado, lo almacena en la tabla de tokens expirados.     * 
     * @see com.tickets.sec.repository.TokensJwtExpiradosRepository
     * 
     * @param token Token JWT.
     * @param credenciales Datos del usuario.
     * 
     * @return boolean True si el token es válido, false en caso contrario.
     */
    public boolean verificarValidezToken(String token, Credenciales credenciales) {
        try {
            final boolean tokenExpirado = verificarTokenExpirado(token);

            if (tokenExpirado) {
                return false;
            } else {
                final String usuario = extraerUsuario(token);
                final boolean usuarioCoincide = usuario.equals(credenciales.getUsuario());
                final boolean tiempoTokenExpirado = verificarTiempoExpiracionToken(token);

                if (tiempoTokenExpirado) {
                    TokensJwtExpirados tokenJwtExpirado = new TokensJwtExpirados();
                    tokenJwtExpirado.setToken(token);
                    tokenJwtExpirado.setCredenciales(credenciales);
                    tokensJwtExpiradosRepository.save(tokenJwtExpirado);
                }

                return (usuarioCoincide && !tiempoTokenExpirado);
            }
        } catch (ExpiredJwtException e) {
            return false;
        }

    }

}
