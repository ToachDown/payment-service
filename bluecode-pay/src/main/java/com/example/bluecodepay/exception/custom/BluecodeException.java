package com.example.bluecodepay.exception.custom;

import com.example.bluecodepay.model.enums.Code;
import com.example.bluecodepay.model.response.PaymentApproved;
import com.example.bluecodepay.model.response.PaymentCanceled;
import com.example.bluecodepay.model.response.PaymentDeclined;
import com.example.bluecodepay.model.response.PaymentFailure;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import template.exception.ApiException;

public class BluecodeException extends ApiException {

    public BluecodeException() {
    }

    public BluecodeException(String message) {
        super(message);
    }

    public BluecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BluecodeException(Throwable cause) {
        super(cause);
    }
}
