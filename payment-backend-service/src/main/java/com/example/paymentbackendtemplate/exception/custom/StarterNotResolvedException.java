package com.example.paymentbackendtemplate.exception.custom;

public class StarterNotResolvedException extends RuntimeException{

    public StarterNotResolvedException() {
    }

    public StarterNotResolvedException(String message) {
        super(message);
    }

    public StarterNotResolvedException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarterNotResolvedException(Throwable cause) {
        super(cause);
    }
}
