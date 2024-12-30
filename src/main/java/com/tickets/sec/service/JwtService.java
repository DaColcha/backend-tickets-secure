package com.tickets.sec.service;

import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.model.Entity.TokensJwtExpirados;
import com.tickets.sec.repository.TokensJwtExpiradosRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.llave-secreta}")
    private String llaveSecreta;

    @Value("${jwt.expiracion-token}")
    private long expiracionToken;

    @Autowired
    TokensJwtExpiradosRepository tokensJwtExpiradosRepository;

    public String generarToken(final Credenciales credenciales) {
        return Jwts.builder()
                .setId(credenciales.getId().toString())
                .setClaims(Map.of("usuario", credenciales.getUsuario()))
                .setSubject(credenciales.getUsuario())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiracionToken))
                .signWith(this.generarLlaveSecreta())
                .compact();
    }

    private SecretKey generarLlaveSecreta() {
        byte[] bytesLlave = Decoders.BASE64.decode(llaveSecreta);
        return Keys.hmacShaKeyFor(bytesLlave);
    }

    public String extraerUsuario(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generarLlaveSecreta())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Date extraerExpiracion(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generarLlaveSecreta())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    private boolean verificarTiempoExpiracionToken(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    private boolean verificarTokenExpirado(String token) {
        return (tokensJwtExpiradosRepository.findByToken(token) != null);
    }

    public boolean verificarValidezToken(String token, Credenciales credenciales) {
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

    }

}
