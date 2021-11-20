package com.example.bluecodepay.implementation.resolver;

import com.example.bluecodepay.client.FeignBluecodeClient;
import com.example.bluecodepay.model.enums.Result;
import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import com.example.bluecodepay.model.response.ResponseBluecodeProcessing;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import com.example.bluecodepay.service.ResilienceService;
import io.github.resilience4j.retry.Retry;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentResolver;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class PaymentResolverBluecode implements PaymentResolver<RequestMessageBluecode, RefundMessageBluecode, TransactionMessageBluecode, ResponseMessageBluecode> {

    private static final String PAYMENT_API_NOT_SUPPORT_OPERATION = "this payment api not support this operation";

    private final FeignBluecodeClient feignBluecodeClient;

    private final ResilienceService resilienceService;


    public PaymentResolverBluecode(final FeignBluecodeClient feignBluecodeClient,
                                   final ResilienceService resilienceService) {
        this.feignBluecodeClient = feignBluecodeClient;
        this.resilienceService = resilienceService;
    }

    @Override
    public ResponseMessageBluecode capturePayment(RequestMessageBluecode request) {
        throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
    }

    @Override
    public ResponseMessageBluecode startPayment(RequestMessageBluecode request) {
        final ResponseMessageBluecode responseMessageBluecode = feignBluecodeClient.startPayment(request);
        if (responseMessageBluecode.getResult().equals(Result.PROCESSING)) {
            ResponseBluecodeProcessing responseBluecodeProcessing = (ResponseBluecodeProcessing) responseMessageBluecode;
            Retry retry = resilienceService.getConfigureRetry(responseBluecodeProcessing.getStatus());
            Supplier<ResponseMessageBluecode> supplier = () -> feignBluecodeClient.statusPayment(Map.of("merchant_tx_id", request.getId()));
            return Retry.decorateSupplier(retry, supplier).get();
        }
        return responseMessageBluecode;
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
