package com.tickets.sec.exceptions;

/**
 * Excepción que se lanza cuando la tarjeta no es válida.
 */
public class CardValidationException extends RuntimeException {
    public CardValidationException(String message) {
        super(message);
    }
}
