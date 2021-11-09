package com.example.paymentbackendtemplate.service;

import org.springframework.stereotype.Component;
import template.interfaces.PaymentResolver;
import template.model.RequestMessage;
import template.model.ResponseMessage;

import java.util.Map;

@Component
public class RequestFacade {

    private final Map<String, PaymentResolver<RequestMessage, ResponseMessage>> paymentResolverMap;

    @SuppressWarnings("SpringJavaInjectonPointsAutowiringInspection")
    public RequestFacade(final Map<String, PaymentResolver<RequestMessage, ResponseMessage>> paymentResolverMap) {
        this.paymentResolverMap = paymentResolverMap;
    }

    public ResponseMessage startTransaction (RequestMessage request) {
        return paymentResolverMap.get(request.getType()).startTransaction(request);
    }

    public ResponseMessage updatePayment (RequestMessage request) {
        return paymentResolverMap.get(request.getType()).updatePayment(request);
    }

    public ResponseMessage captureTransaction (RequestMessage request) {
        return paymentResolverMap.get(request.getType()).captureTransaction(request);
    }
}
