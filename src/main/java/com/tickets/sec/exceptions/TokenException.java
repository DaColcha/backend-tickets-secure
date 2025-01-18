package com.tickets.sec.exceptions;

/**
 * Excepción que se lanza cuando el token no es válido.
 */
public class TokenException extends RuntimeException {
    public TokenException(String message) {
        super(message);
    }
}
