package com.example.bluecodepay.exception.custom;

import template.exception.ApiFeignException;

public class BluecodeFeignException extends ApiFeignException {

    public BluecodeFeignException() {
    }

    public BluecodeFeignException(String message) {
        super(message);
    }

    public BluecodeFeignException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeFeignException(Throwable cause) {
        super(cause);
    }
}
