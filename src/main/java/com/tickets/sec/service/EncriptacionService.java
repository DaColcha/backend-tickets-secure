package com.tickets.sec.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncriptacionService {

    private TextEncryptor encryptor;

    @Value("${encryptor.password}")
    private String encryptorPassword;

    @Value("${encryptor.salt}")
    private String encryptorSalt;

    @PostConstruct
    private void initializeEncryptor() {
        encryptor = Encryptors.text(encryptorPassword, encryptorSalt);
    }

    public String encriptar(String cadena) {
        return encryptor.encrypt(cadena);
    }

    public String desencriptar(String cadenaEncriptada) {
        return encryptor.decrypt(cadenaEncriptada);
    }

}
