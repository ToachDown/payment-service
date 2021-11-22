package com.example.paymentbackendtemplate.service;

import org.springframework.stereotype.Component;
import template.exception.ApiException;
import template.interfaces.PaymentResolver;
import template.interfaces.PaymentUpdater;
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
    private final Map<String, PaymentUpdater<RequestMessage, ResponseMessage>> paymentStateChangerMap;

    @SuppressWarnings("SpringJavaInjectonPointsAutowiringInspection")
    public RequestCommander(final Map<String, PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage>> paymentResolverMap,
                            final Map<String, PaymentUpdater<RequestMessage, ResponseMessage>> paymentStateChangerMap) {
        this.paymentResolverMap = paymentResolverMap;
        this.paymentStateChangerMap = paymentStateChangerMap;
    }

    public ResponseMessage startTransaction(RequestMessage request) throws ApiException {
        return paymentResolverMap.get(request.getType()).startPayment(request);
    }

    public ResponseMessage updatePayment(RequestMessage request) throws ApiException{
        return paymentResolverMap.get(request.getType()).updatePayment(request);
    }

    public ResponseMessage captureTransaction(RequestMessage request) throws ApiException{
        return paymentResolverMap.get(request.getType()).capturePayment(request);
    }

    public ResponseMessage statusTransaction(TransactionMessage request) throws ApiException {
        return paymentResolverMap.get(request.getType()).statusPayment(request);
    }

    public ResponseMessage cancelTransaction(TransactionMessage request) throws ApiException{
        return paymentResolverMap.get(request.getType()).cancelPayment(request);
    }

    public ResponseMessage refundPayment(RefundMessage request) throws ApiException {
        return paymentResolverMap.get(request.getType()).refundPayment(request);
    }

    public RequestMessage changePaymentStateWithResponse(RequestMessage request, ResponseMessage response) throws ApiException{
        return paymentStateChangerMap.get(request.getType()).changeStateWithResponse(request, response);
    }

    public RequestMessage changePaymentState(RequestMessage request, String state) throws ApiException{
        return paymentStateChangerMap.get(request.getType()).changeState(request, state);
    }
}
