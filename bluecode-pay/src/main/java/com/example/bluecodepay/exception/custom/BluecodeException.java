package com.example.bluecodepay.exception.custom;

import template.exception.ApiException;

public class BluecodeException extends ApiException {

    public BluecodeException() {
    }

    public BluecodeException(String message) {
        super(message);
    }

    public BluecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeException(Throwable cause) {
        super(cause);
    }
}
