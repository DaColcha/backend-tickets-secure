package com.tickets.sec.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

/**
 * Clase que proporciona métodos para encriptar y desencriptar cadenas de texto.
 */
@Service
public class EncriptacionService {

    private TextEncryptor encryptor;

    @Value("${encryptor.password}")
    private String encryptorPassword;

    @Value("${encryptor.salt}")
    private String encryptorSalt;

    /**
     * Inicializa el cifrador de texto en base a la contraseña y el "salt" almacenado en el archivo de propiedades.
     */
    @PostConstruct
    private void initializeEncryptor() {
        encryptor = Encryptors.text(encryptorPassword, encryptorSalt);
    }

    /**
     * Encripta una cadena de texto.
     * @see org.springframework.security.crypto.encrypt.TextEncryptor
     * 
     * @param cadena Cadena de texto a encriptar.
     * 
     * @return java.lang.String Cadena de texto encriptada.
     */
    public String encriptar(String cadena) {
        return encryptor.encrypt(cadena);
    }

    /**
     * Desencripta una cadena de texto.
     * @see org.springframework.security.crypto.encrypt.TextEncryptor
     * 
     * @param cadenaEncriptada Cadena de texto encriptada.
     * 
     * @return java.lang.String Cadena de texto desencriptada.
     */
    public String desencriptar(String cadenaEncriptada) {
        return encryptor.decrypt(cadenaEncriptada);
    }

}
