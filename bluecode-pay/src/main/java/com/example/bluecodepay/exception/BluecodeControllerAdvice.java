package com.example.bluecodepay.exception;

import com.example.bluecodepay.exception.custom.BlucodeFeignException;
import com.example.bluecodepay.exception.custom.BluecodeSystemException;
import com.example.bluecodepay.exception.custom.BluecodeTransformException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BluecodeControllerAdvice {

    @ExceptionHandler(BlucodeFeignException.class)
    public ResponseEntity<String> handleBadRequest(BlucodeFeignException blucodeFeignException) {
        return new ResponseEntity<>(blucodeFeignException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BluecodeTransformException.class)
    public ResponseEntity<String> handlerBadTransform(BluecodeTransformException bluecodeFeignException) {
        return new ResponseEntity<>("system cannot convert you request: " + bluecodeFeignException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BluecodeSystemException.class)
    public ResponseEntity<String> handlerSystemError(BluecodeSystemException bluecodeSystemException) {
        return new ResponseEntity<>("system error: " + bluecodeSystemException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
