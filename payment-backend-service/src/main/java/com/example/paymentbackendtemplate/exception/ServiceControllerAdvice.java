package com.example.paymentbackendtemplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceControllerAdvice {

    @ExceptionHandler({DataBaseException.class})
    public ResponseEntity<String> dataBaseNotFoundHandler(DataBaseException dataBaseException) {
        return new ResponseEntity<String>("Not found entity in database: " + dataBaseException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
