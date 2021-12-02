package com.example.backendtemplate.exception;

public class ApiFeignException extends RuntimeException {

    public ApiFeignException() {
    }

    public ApiFeignException(String message) {
        super(message);
    }

    public ApiFeignException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiFeignException(Throwable cause) {
        super(cause);
    }

}
