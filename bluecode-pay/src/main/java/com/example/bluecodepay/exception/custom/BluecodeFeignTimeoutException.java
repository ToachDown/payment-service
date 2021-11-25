package com.example.bluecodepay.exception.custom;

public class BluecodeFeignTimeoutException extends BluecodeFeignException{

    public BluecodeFeignTimeoutException() {
    }

    public BluecodeFeignTimeoutException(String message) {
        super(message);
    }

    public BluecodeFeignTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeFeignTimeoutException(Throwable cause) {
        super(cause);
    }
}
