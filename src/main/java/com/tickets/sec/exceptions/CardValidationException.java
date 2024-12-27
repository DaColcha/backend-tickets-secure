package com.tickets.sec.exceptions;

public class CardValidationException extends RuntimeException {
    public CardValidationException(String message) {
        super(message);
    }
}
