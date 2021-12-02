package com.example.paymentbackendtemplate.implementation;

import com.example.backendtemplate.model.ResponseMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class ResponseMessageTest extends ResponseMessage {

    public ResponseMessageTest() {
    }
}
