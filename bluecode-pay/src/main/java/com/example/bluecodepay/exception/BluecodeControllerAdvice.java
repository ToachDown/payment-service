package com.example.bluecodepay.exception;

import com.example.bluecodepay.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BluecodeControllerAdvice {

    @ExceptionHandler(BluecodeFeignInternalException.class)
    public ResponseEntity<String> handlerSystemError(BluecodeFeignInternalException blucodeFeignException) {
        return new ResponseEntity<>("System error: " + blucodeFeignException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BluecodeFeignNotFoundException.class)
    public ResponseEntity<String> handlerBadTransform(BluecodeFeignNotFoundException bluecodeFeignException) {
        return new ResponseEntity<>("System not found for you request: " + bluecodeFeignException.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BluecodeFeignBadRequestException.class)
    public ResponseEntity<String> handlerBadRequest(BluecodeFeignBadRequestException bluecodeFeignBadRequestException) {
        return new ResponseEntity<>("You input bad request: " + bluecodeFeignBadRequestException.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BluecodeFeignTimeoutException.class)
    public ResponseEntity<String> handlerTimout(BluecodeFeignTimeoutException bluecodeFeignTimeoutException) {
        return new ResponseEntity<>("Connection interrupt: " + bluecodeFeignTimeoutException.getMessage(),
                HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(BluecodeFeignUnauthorizedException.class)
    public ResponseEntity<String> handlerUnauthorized(BluecodeFeignUnauthorizedException bluecodeFeignUnauthorizedException) {
        return new ResponseEntity<>("You unauthorized: " + bluecodeFeignUnauthorizedException.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BluecodeFeignUnsupportedMediaTypeException.class)
    public ResponseEntity<String> handlerUnsupportedMediaType(BluecodeFeignUnsupportedMediaTypeException bluecodeFeignUnsupportedMediaTypeException) {
        return new ResponseEntity<>("You use unsupported media type: " + bluecodeFeignUnsupportedMediaTypeException.getMessage(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(BluecodeFeignUnsupportedMethodException.class)
    public ResponseEntity<String> handlerUnsupportedMethod(BluecodeFeignUnsupportedMethodException bluecodeFeignUnsupportedMethodException) {
        return new ResponseEntity<>("This end point not supported: " + bluecodeFeignUnsupportedMethodException.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(BluecodeTransformBadParametersException.class)
    public ResponseEntity<String> handlerBadParameters(BluecodeTransformBadParametersException bluecodeTransformBadParametersException) {
        return new ResponseEntity<>("invalid request parameters: " + bluecodeTransformBadParametersException.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
