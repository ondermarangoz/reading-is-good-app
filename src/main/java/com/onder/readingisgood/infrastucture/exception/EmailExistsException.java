package com.onder.readingisgood.infrastucture.exception;


public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }
}