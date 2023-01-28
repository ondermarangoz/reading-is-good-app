package com.onder.readingisgood.infrastucture.exception;

public class EmailNotValidException extends RuntimeException {
    public EmailNotValidException(String message) {
        super(message);
    }
}