package com.example.bluecodepay.service.updater;

import com.example.bluecodepay.model.enums.State;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.response.BluecodeResponseMessageOk;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentUpdater;

@Component
public class BluecodePaymentUpdater implements PaymentUpdater<BluecodeRequestMessage, BluecodeResponseMessageOk> {

    @Override
    public BluecodeRequestMessage changeStateWithResponse(BluecodeRequestMessage request, BluecodeResponseMessageOk response) {
        request.setState(response.getPayment().getState());
        return request;
    }

    @Override
    public BluecodeRequestMessage changeState(BluecodeRequestMessage request, String state) {
        request.setState(State.valueOf(state));
        return request;
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
