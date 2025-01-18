package com.tickets.sec.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tickets.sec.dto.PaymentData;
import com.tickets.sec.dto.Tarjeta;
import com.tickets.sec.exceptions.CardValidationException;
import com.tickets.sec.exceptions.TokenException;
import com.tickets.sec.model.Entity.TokenData;
import com.tickets.sec.repository.TokenRepository;
import com.tickets.sec.utils.CryptoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

/**
 * Servicio para la creación y validación de tokens de pago.
 */
@Service
@Slf4j
public class TokenizationService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CryptoUtils cryptoUtils;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Crea un token de pago a partir de los datos de pago encriptados.
     * @param encryptedData
     * @return
     */
    public String createToken(PaymentData encryptedData) {

        if(encryptedData.getUseCard()) {
            try{
                String decryptedData = cryptoUtils.decrypt(encryptedData.getEncryptedData());
                Tarjeta cardData = objectMapper.readValue(decryptedData, Tarjeta.class);

                // Validar datos de tarjeta
                validateCard(cardData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        String token = generateSecureToken();

        TokenData tokenData = new TokenData();
        tokenData.setToken(token);
        tokenData.setCreatedAt(LocalDateTime.now());
        tokenData.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        tokenData.setUsed(false);

        tokenRepository.save(tokenData);
        log.info("Se ha creado un token de pago.");

        return token;
    }

    /**
     * Valida un token de pago y devuelve los datos del token.
     * @param token
     * @return
     * @throws TokenException
     */
    public TokenData validateAndGetToken(String token) throws TokenException {
        TokenData tokenData = tokenRepository.findByToken(token);

        if (tokenData.isUsed()) {
            throw new TokenException("El token ya ha sido utilizado");
        }

        if (tokenData.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokenException("El token ha expirado");
        }

        return tokenData;
    }

    /**
     * Genera un token seguro.
     * @return
     */
    private String generateSecureToken() {
        return UUID.randomUUID() +
                RandomStringUtils.randomAlphanumeric(32);
    }

    /**
     * Valida los datos de la tarjeta.
     * @param tarjeta
     * @throws CardValidationException
     */
    private void validateCard(Tarjeta tarjeta) throws CardValidationException {

        if (!isValidCardNumber(tarjeta.getNumero())) {
            throw new CardValidationException("Numero de tarjeta no válido");
        }

        if (!isValidExpiryDate(tarjeta.getFechaVencimiento())) {
            throw new CardValidationException("Fecha de vencimiento no válida");
        }

        if(tarjeta.getCvv().length() != 3) {
            throw new CardValidationException("CVV no válido");
        }
    }

    /**
     * Valida el número de tarjeta de crédito.
     * @param cardNumber
     * @return
     */
    public boolean isValidCardNumber(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(cardNumber.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    /**
     * Valida la fecha de vencimiento de la tarjeta.
     * @param expiryDate
     * @return
     */
    public boolean isValidExpiryDate(String expiryDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        try {
            YearMonth expiry = YearMonth.parse(expiryDate, formatter);
            return !expiry.isBefore(YearMonth.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}