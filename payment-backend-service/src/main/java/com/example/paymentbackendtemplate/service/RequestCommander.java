package com.example.paymentbackendtemplate.service;

import org.springframework.stereotype.Component;
import template.interfaces.resolver.*;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;

import java.util.Map;

@Component
public class RequestCommander {

    private static final String PAYMENT_API_NOT_SUPPORT_OPERATION = "this payment api not support this operation";

    private final Map<String, StartPaymentResolver<RequestMessage, ResponseMessage>> startPaymentResolverMap;
    private final Map<String, StatusPaymentResolver<TransactionMessage, ResponseMessage>> statusPaymentResolverMap;
    private final Map<String, CancelPaymentResolver<TransactionMessage, ResponseMessage>> cancelPaymentResolverMap;
    private final Map<String, RefundPaymentResolver<RefundMessage, ResponseMessage>> refundPaymentResolverMap;
    private final Map<String, UpdatePaymentResolver<RequestMessage, ResponseMessage>> updatePaymentResolverMap;
    private final Map<String, CapturePaymentResolver<RequestMessage, ResponseMessage>> capturePaymentResolverMap;

    @SuppressWarnings("SpringJavaInjectonPointsAutowiringInspection")
    public RequestCommander(final Map<String, StartPaymentResolver<RequestMessage, ResponseMessage>> startPaymentResolverMap,
                            final Map<String, StatusPaymentResolver<TransactionMessage, ResponseMessage>> statusPaymentResolverMap,
                            final Map<String, CancelPaymentResolver<TransactionMessage, ResponseMessage>> cancelPaymentResolverMap,
                            final Map<String, RefundPaymentResolver<RefundMessage, ResponseMessage>> refundPaymentResolverMap,
                            final Map<String, UpdatePaymentResolver<RequestMessage, ResponseMessage>> updatePaymentResolverMap,
                            final Map<String, CapturePaymentResolver<RequestMessage, ResponseMessage>> capturePaymentResolverMap) {
        this.startPaymentResolverMap = startPaymentResolverMap;
        this.statusPaymentResolverMap = statusPaymentResolverMap;
        this.cancelPaymentResolverMap = cancelPaymentResolverMap;
        this.refundPaymentResolverMap = refundPaymentResolverMap;
        this.updatePaymentResolverMap = updatePaymentResolverMap;
        this.capturePaymentResolverMap = capturePaymentResolverMap;
    }

    public ResponseMessage startTransaction(RequestMessage request) {
        final StartPaymentResolver<RequestMessage, ResponseMessage> resolver = startPaymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
        }
        return resolver.startTransaction(request);
    }

    public ResponseMessage updatePayment(RequestMessage request) {
        final UpdatePaymentResolver<RequestMessage, ResponseMessage> resolver = updatePaymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
        }
        return resolver.updatePayment(request);
    }

    public ResponseMessage captureTransaction(RequestMessage request) {
        final CapturePaymentResolver<RequestMessage, ResponseMessage> resolver = capturePaymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
        }
        return resolver.capturePayment(request);
    }

    public ResponseMessage statusTransaction(TransactionMessage request) {
        final StatusPaymentResolver<TransactionMessage, ResponseMessage> resolver = statusPaymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
        }
        return resolver.statusTransaction(request);
    }

    public ResponseMessage cancelTransaction(TransactionMessage request) {
        final CancelPaymentResolver<TransactionMessage, ResponseMessage> resolver = cancelPaymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
        }
        return resolver.cancelTransaction(request);
    }

    public ResponseMessage refundPayment(RefundMessage request) {
        final RefundPaymentResolver<RefundMessage, ResponseMessage> resolver = refundPaymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
        }
        return resolver.refundPayment(request);
    }
}
