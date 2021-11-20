package com.example.bluecodepay.implementation.changer;

import com.example.bluecodepay.model.enums.State;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseBluecodeOk;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentUpdater;

@Component
public class PaymentUpdaterBluecode implements PaymentUpdater<RequestMessageBluecode, ResponseBluecodeOk> {

    @Override
    public RequestMessageBluecode changeStateWithResponse(RequestMessageBluecode request, ResponseBluecodeOk response) {
        request.setState(response.getPayment().getState());
        return request;
    }

    @Override
    public RequestMessageBluecode changeState(RequestMessageBluecode request, String state) {
        request.setState(State.valueOf(state));
        return request;
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
