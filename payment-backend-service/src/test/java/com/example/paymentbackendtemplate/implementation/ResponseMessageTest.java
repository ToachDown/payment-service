package com.example.paymentbackendtemplate.implementation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import template.model.ResponseMessage;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class ResponseMessageTest extends ResponseMessage {

    public ResponseMessageTest() {
    }
}
