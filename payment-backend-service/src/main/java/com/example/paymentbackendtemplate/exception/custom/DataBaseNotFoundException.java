package com.example.paymentbackendtemplate.exception.custom;

public class DataBaseNotFoundException extends RuntimeException {

    public DataBaseNotFoundException() {
    }

    public DataBaseNotFoundException(String message) {
        super(message);
    }

    public DataBaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseNotFoundException(Throwable cause) {
        super(cause);
    }
}
