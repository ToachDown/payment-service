package com.example.bluecodepay.service;

import com.example.bluecodepay.model.RequestMessageBluecode;
import com.example.bluecodepay.model.ResponseMessageBluecode;
import org.springframework.stereotype.Component;
import template.interfaces.RequestDelegator;

@Component
public class RequestDelegatorImpl implements RequestDelegator<RequestMessageBluecode, ResponseMessageBluecode> {

    @Override
    public ResponseMessageBluecode getAnswer(RequestMessageBluecode request) {
        return new ResponseMessageBluecode();
    }

    @Override
    public String getType() {
        return "bluecode-pay";
    }
}
