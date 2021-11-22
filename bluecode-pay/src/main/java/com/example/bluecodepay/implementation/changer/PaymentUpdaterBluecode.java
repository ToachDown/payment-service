package com.example.bluecodepay.implementation.changer;

import com.example.bluecodepay.exception.custom.BluecodeSystemException;
import com.example.bluecodepay.model.enums.State;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseBluecodeOk;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentUpdater;

@Component
public class PaymentUpdaterBluecode implements PaymentUpdater<RequestMessageBluecode, ResponseBluecodeOk> {

    @Override
    public RequestMessageBluecode changeStateWithResponse(RequestMessageBluecode request, ResponseBluecodeOk response) throws BluecodeSystemException {
        try {
            request.setState(response.getPayment().getState());
            return request;
        } catch (Exception e) {
            throw new BluecodeSystemException("cannot change payment state from response: " + e.getMessage());
        }
    }

    @Override
    public RequestMessageBluecode changeState(RequestMessageBluecode request, String state) throws BluecodeSystemException {
        try {
            request.setState(State.valueOf(state));
            return request;
        } catch (Exception e) {
            throw new BluecodeSystemException("cannot change payment state: " + e.getMessage());
        }
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
