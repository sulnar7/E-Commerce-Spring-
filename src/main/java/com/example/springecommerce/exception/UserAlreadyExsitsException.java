package com.example.springecommerce.exception;

public class UserAlreadyExsitsException extends RuntimeException {
    public UserAlreadyExsitsException(String message) {
        super(message);
    }
}
