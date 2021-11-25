package com.example.bluecodepay.exception.custom;

public class BluecodeFeignInternalException extends BluecodeFeignException {

    public BluecodeFeignInternalException() {
    }

    public BluecodeFeignInternalException(String message) {
        super(message);
    }

    public BluecodeFeignInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeFeignInternalException(Throwable cause) {
        super(cause);
    }
}
