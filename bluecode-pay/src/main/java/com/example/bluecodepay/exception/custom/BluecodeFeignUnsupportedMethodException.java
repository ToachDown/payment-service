package com.example.bluecodepay.exception.custom;

import template.exception.ApiFeignException;
import template.exception.ApiTransformException;

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
