package com.example.bluecodepay.exception.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "illegal request")
public class BlucodeFeignException extends BluecodeException {

    public BlucodeFeignException() {
    }

    public BlucodeFeignException(String message) {
        super(message);
    }

    public BlucodeFeignException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlucodeFeignException(Throwable cause) {
        super(cause);
    }
}
