package com.example.bluecodepay.exception.custom;

public class BluecodeFeignUnsupportedMediaTypeException extends BluecodeFeignException {

    public BluecodeFeignUnsupportedMediaTypeException() {
    }

    public BluecodeFeignUnsupportedMediaTypeException(String message) {
        super(message);
    }

    public BluecodeFeignUnsupportedMediaTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeFeignUnsupportedMediaTypeException(Throwable cause) {
        super(cause);
    }
}
