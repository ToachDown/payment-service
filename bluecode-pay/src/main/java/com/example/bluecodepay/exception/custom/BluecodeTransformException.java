package com.example.bluecodepay.exception.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "cannot transform dto to entity")
public class BluecodeTransformException extends BluecodeException{

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
