package com.example.paymentbackendtemplate.service;

import com.example.bluecodepay.service.RequestDelegatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import template.interfaces.RequestDelegator;
import template.model.RequestMessage;
import template.model.ResponseMessage;

import java.util.Map;

@Component
public class RequestFacade {

    private final Map<String, RequestDelegator<RequestMessage, ResponseMessage>> requestDelegatorMap;

    @SuppressWarnings("SpringJavaInjectonPointsAutowiringInspection")
    public RequestFacade(final Map<String, RequestDelegator<RequestMessage, ResponseMessage>> requestsDelegatorMap) {
        this.requestDelegatorMap = requestsDelegatorMap;
    }

    public ResponseMessage getAnswer (RequestMessage request) {
        var a = requestDelegatorMap.get(request.getType()).getAnswer(request);
        return a;
    }
}
