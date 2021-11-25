package com.example.bluecodepay.exception.custom;

public class BlucodeFeignInternalException extends BluecodeFeignException {

    public BlucodeFeignInternalException() {
    }

    public BlucodeFeignInternalException(String message) {
        super(message);
    }

    public BlucodeFeignInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlucodeFeignInternalException(Throwable cause) {
        super(cause);
    }
}
