package com.example.bluecodepay.exception.custom;

public class BluecodeFeignNotFoundException extends BluecodeFeignException {

    public BluecodeFeignNotFoundException() {
    }

    public BluecodeFeignNotFoundException(String message) {
        super(message);
    }

    public BluecodeFeignNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeFeignNotFoundException(Throwable cause) {
        super(cause);
    }
}
