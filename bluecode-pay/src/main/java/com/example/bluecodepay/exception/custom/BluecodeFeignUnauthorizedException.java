package com.example.bluecodepay.exception.custom;

public class BluecodeFeignUnauthorizedException extends BluecodeFeignException {

    public BluecodeFeignUnauthorizedException() {
    }

    public BluecodeFeignUnauthorizedException(String message) {
        super(message);
    }

    public BluecodeFeignUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeFeignUnauthorizedException(Throwable cause) {
        super(cause);
    }
}
