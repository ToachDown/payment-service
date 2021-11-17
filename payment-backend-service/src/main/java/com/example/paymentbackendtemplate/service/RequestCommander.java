package com.example.paymentbackendtemplate.service;

import org.springframework.stereotype.Component;
import template.interfaces.PaymentResolver;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;

import java.util.Map;

@Component
public class RequestCommander {

    private final Map<String, PaymentResolver<
            RequestMessage,
            RefundMessage,
            TransactionMessage,
            ResponseMessage>> paymentResolverMap;

    @SuppressWarnings("SpringJavaInjectonPointsAutowiringInspection")
    public RequestCommander(Map<String, PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage>> paymentResolverMap) {
        this.paymentResolverMap = paymentResolverMap;
    }

    public ResponseMessage startTransaction(RequestMessage request) {
        return paymentResolverMap.get(request.getType()).startPayment(request);
    }

    public ResponseMessage updatePayment(RequestMessage request) {
        return paymentResolverMap.get(request.getType()).updatePayment(request);
    }

    public ResponseMessage captureTransaction(RequestMessage request) {
        return paymentResolverMap.get(request.getType()).capturePayment(request);
    }

    public ResponseMessage statusTransaction(TransactionMessage request) {
        return paymentResolverMap.get(request.getType()).statusPayment(request);
    }

    public ResponseMessage cancelTransaction(TransactionMessage request) {
        return paymentResolverMap.get(request.getType()).cancelPayment(request);
    }

    public ResponseMessage refundPayment(RefundMessage request) {
        return paymentResolverMap.get(request.getType()).refundPayment(request);
    }
}
