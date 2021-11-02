package com.example.bluecodepay.model;

import org.springframework.stereotype.Component;
import template.model.RequestMessage;

public class RequestMessageBluecode extends RequestMessage {

    public RequestMessageBluecode() {
        super("bluecode-pay");
    }
}
