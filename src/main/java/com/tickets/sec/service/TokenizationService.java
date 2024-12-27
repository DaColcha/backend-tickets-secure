package com.tickets.sec.service;

import com.tickets.sec.dto.Tarjeta;
import com.tickets.sec.exceptions.CardValidationException;
import com.tickets.sec.exceptions.TokenException;
import com.tickets.sec.model.Entity.TokenData;
import com.tickets.sec.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Service
@Slf4j
public class TokenizationService {

    @Autowired
    private TokenRepository tokenRepository;

    public String createToken(Tarjeta cardData) throws CardValidationException {
        validateCard(cardData);

        String token = generateSecureToken();

        TokenData tokenData = new TokenData();
        tokenData.setToken(token);
        tokenData.setCreatedAt(LocalDateTime.now());
        tokenData.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        tokenData.setUsed(false);

        tokenRepository.save(tokenData);
        log.info("Token created successfully for card ending in {}",
                cardData.getNumero().substring(12));

        return token;
    }

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

    private String generateSecureToken() {
        return UUID.randomUUID().toString() +
                RandomStringUtils.randomAlphanumeric(32);
    }

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