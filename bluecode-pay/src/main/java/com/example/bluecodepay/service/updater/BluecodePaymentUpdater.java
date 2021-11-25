package com.example.bluecodepay.service.updater;

import com.example.bluecodepay.model.enums.State;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.response.BluecodeResponseOkResponseMessage;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentUpdater;

@Component
public class BluecodePaymentUpdater implements PaymentUpdater<BluecodeRequestMessage, BluecodeResponseOkResponseMessage> {

    @Override
    public BluecodeRequestMessage changeStateWithResponse(BluecodeRequestMessage request, BluecodeResponseOkResponseMessage response) {
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
