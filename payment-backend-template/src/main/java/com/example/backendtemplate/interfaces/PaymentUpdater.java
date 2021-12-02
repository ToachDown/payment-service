package com.example.backendtemplate.interfaces;

import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.ResponseMessage;

public interface PaymentUpdater<R extends RequestMessage, T extends ResponseMessage> {

    R changeStateWithResponse(R request, T response);

    R changeState(R request, String state);

    String getType();
}
