package com.example.paymentbackendtemplate.exception;

import com.example.paymentbackendtemplate.exception.custom.DataBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ServiceControllerAdvice {

    @ExceptionHandler({DataBaseException.class})
    public ResponseEntity<String> dataBaseNotFoundHandler(DataBaseException dataBaseException) {
        return new ResponseEntity<String>("Not found entity in database: " + dataBaseException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    public ResponseEntity<String> httpMessageConversionException(HttpMessageConversionException httpMessageNotReadableException) {
        return new ResponseEntity<String>("request fields unreadable : " + httpMessageNotReadableException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<String> httpMessageNotReadableHandler(HttpMessageNotReadableException httpMessageNotReadableException) {
        return new ResponseEntity<String>("request fields unreadable : " + httpMessageNotReadableException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NestedServletException.class})
    public ResponseEntity<String> httpMessageNotReadableHandler(NestedServletException httpMessageNotReadableException) {
        return new ResponseEntity<String>("request fields unreadable : " + httpMessageNotReadableException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> methodArgumentNotValidHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorFields = methodArgumentNotValidException.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " : " + fieldError.getRejectedValue())
                .collect(Collectors.toList());
        return new ResponseEntity<String>("request fields invalid: " + errorFields, HttpStatus.BAD_REQUEST);
    }
}
