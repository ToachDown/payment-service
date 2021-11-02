package com.example.bluecodepay.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import template.model.ResponseMessage;

@JsonAppend
public class ResponseMessageBluecode extends ResponseMessage {

    @JsonView
    private String mess;

    public ResponseMessageBluecode() {
        this.mess = "you winner";
    }
}
