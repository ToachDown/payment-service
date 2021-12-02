package com.example.bluecodepay.exception.custom;

import com.example.backendtemplate.exception.ApiFeignException;

public class BluecodeFeignUnsupportedMethodException extends ApiFeignException {

    public BluecodeFeignUnsupportedMethodException() {
    }

    public BluecodeFeignUnsupportedMethodException(String message) {
        super(message);
    }

    public BluecodeFeignUnsupportedMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeFeignUnsupportedMethodException(Throwable cause) {
        super(cause);
    }
}
