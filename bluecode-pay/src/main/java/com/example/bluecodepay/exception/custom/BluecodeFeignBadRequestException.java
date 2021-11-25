package com.example.bluecodepay.exception.custom;

public class BluecodeFeignBadRequestException extends BluecodeFeignException {

    public BluecodeFeignBadRequestException() {
    }

    public BluecodeFeignBadRequestException(String message) {
        super(message);
    }

    public BluecodeFeignBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeFeignBadRequestException(Throwable cause) {
        super(cause);
    }
}
