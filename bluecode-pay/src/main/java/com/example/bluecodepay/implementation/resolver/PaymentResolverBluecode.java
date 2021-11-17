package com.example.bluecodepay.implementation.resolver;

import com.example.bluecodepay.client.FeignBluecodeClient;
import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentResolver;

import java.util.Collections;

@Component
public class PaymentResolverBluecode implements PaymentResolver<RequestMessageBluecode, RefundMessageBluecode, TransactionMessageBluecode, ResponseMessageBluecode> {

    private static final String PAYMENT_API_NOT_SUPPORT_OPERATION = "this payment api not support this operation";

    private final FeignBluecodeClient feignBluecodeClient;

    public PaymentResolverBluecode(FeignBluecodeClient feignBluecodeClient) {
        this.feignBluecodeClient = feignBluecodeClient;
    }

    @Override
    public ResponseMessageBluecode capturePayment(RequestMessageBluecode request) {
        throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
    }

    @Override
    public ResponseMessageBluecode startPayment(RequestMessageBluecode request) {
        return feignBluecodeClient.startPayment(request);
    }

    @Override
    public ResponseMessageBluecode updatePayment(RequestMessageBluecode request) {
        throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
    }

    @Override
    public ResponseMessageBluecode refundPayment(RefundMessageBluecode request) {
        return feignBluecodeClient.refundPayment(request);
    }

    @Override
    public ResponseMessageBluecode cancelPayment(TransactionMessageBluecode request) {
        return feignBluecodeClient.cancelPayment(Collections.singletonMap("merchant_tx_id", request.getTransactionId()));
    }

    @Override
    public ResponseMessageBluecode statusPayment(TransactionMessageBluecode request) {
        return feignBluecodeClient.statusPayment(Collections.singletonMap("merchant_tx_id", request.getTransactionId()));
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
