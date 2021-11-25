package com.example.bluecodepay.exception.custom;

public class BluecodeTransformBadParametersException extends BluecodeTransformException{

    public BluecodeTransformBadParametersException() {
    }

    public BluecodeTransformBadParametersException(String message) {
        super(message);
    }

    public BluecodeTransformBadParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeTransformBadParametersException(Throwable cause) {
        super(cause);
    }
}
