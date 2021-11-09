package com.example.bluecodepay.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import template.model.ResponseMessage;

@Getter
@Setter
public class ResponseMessageBluecode extends ResponseMessage {

    private String result;
//    private ResponseDetails paymentDetails;

    @JsonCreator
    public ResponseMessageBluecode(@JsonProperty("result") String result) {
        this.result = result;
    }
}
