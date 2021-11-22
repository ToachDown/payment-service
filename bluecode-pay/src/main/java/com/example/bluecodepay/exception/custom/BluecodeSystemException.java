package com.example.bluecodepay.exception.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "system error")
public class BluecodeSystemException extends BluecodeException{

    public BluecodeSystemException() {
    }

    public BluecodeSystemException(String message) {
        super(message);
    }

    public BluecodeSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeSystemException(Throwable cause) {
        super(cause);
    }
}
