package com.example.bluecodepay.exception.custom;

import template.exception.ApiTransformException;

public class BluecodeTransformException extends ApiTransformException {

    public BluecodeTransformException() {
    }

    public BluecodeTransformException(String message) {
        super(message);
    }

    public BluecodeTransformException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeTransformException(Throwable cause) {
        super(cause);
    }
}
