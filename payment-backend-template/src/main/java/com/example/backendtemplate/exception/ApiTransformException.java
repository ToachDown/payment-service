package com.example.backendtemplate.exception;

public class ApiTransformException extends RuntimeException {

    public ApiTransformException() {
    }

    public ApiTransformException(String message) {
        super(message);
    }

    public ApiTransformException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiTransformException(Throwable cause) {
        super(cause);
    }
}
