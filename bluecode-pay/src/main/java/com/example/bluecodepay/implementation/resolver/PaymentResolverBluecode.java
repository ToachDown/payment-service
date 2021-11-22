package com.example.bluecodepay.implementation.resolver;

import com.example.bluecodepay.client.FeignBluecodeClient;
import com.example.bluecodepay.configuration.ResilienceConfig;
import com.example.bluecodepay.exception.custom.BlucodeFeignException;
import com.example.bluecodepay.exception.custom.BluecodeException;
import com.example.bluecodepay.exception.custom.BluecodeSystemException;
import com.example.bluecodepay.model.enums.Result;
import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import com.example.bluecodepay.model.response.ResponseBluecodeProcessing;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import feign.FeignException;
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

    private final ResilienceConfig resilienceConfig;


    public PaymentResolverBluecode(final FeignBluecodeClient feignBluecodeClient,
                                   final ResilienceConfig resilienceConfig) {
        this.feignBluecodeClient = feignBluecodeClient;
        this.resilienceConfig = resilienceConfig;
    }

    @Override
    public ResponseMessageBluecode capturePayment(RequestMessageBluecode request) {
        throw new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION);
    }

    @Override
    public ResponseMessageBluecode startPayment(RequestMessageBluecode request) throws BluecodeException {
        try {
            final ResponseMessageBluecode responseMessageBluecode = feignBluecodeClient.startPayment(request);
            if (responseMessageBluecode.getResult().equals(Result.PROCESSING)) {
                ResponseBluecodeProcessing responseBluecodeProcessing = (ResponseBluecodeProcessing) responseMessageBluecode;
                Retry retry = resilienceConfig.getConfigureRetry(responseBluecodeProcessing.getStatus());
                Supplier<ResponseMessageBluecode> supplier = () -> feignBluecodeClient.statusPayment(Map.of("merchant_tx_id", request.getId()));
                return Retry.decorateSupplier(retry, supplier).get();
            }
            return responseMessageBluecode;
        } catch (FeignException e) {
            throw new BlucodeFeignException(e.contentUTF8());
        } catch (Exception e) {
            throw new BluecodeSystemException(e);
        }
    }

    @Override
    public ResponseMessageBluecode updatePayment(RequestMessageBluecode request) throws BlucodeFeignException {
        throw new BlucodeFeignException(new UnsupportedOperationException(PAYMENT_API_NOT_SUPPORT_OPERATION));
    }

    @Override
    public ResponseMessageBluecode refundPayment(RefundMessageBluecode request) throws BluecodeException {
        try {
            return feignBluecodeClient.refundPayment(request);
        } catch (FeignException e) {
            throw new BlucodeFeignException(e.contentUTF8());
        } catch (Exception e) {
            throw new BluecodeSystemException(e);
        }
    }

    @Override
    public ResponseMessageBluecode cancelPayment(TransactionMessageBluecode request) throws BluecodeException {
        try {
            return feignBluecodeClient.cancelPayment(Collections.singletonMap("merchant_tx_id", request.getTransactionId()));
        } catch (FeignException e) {
            throw new BlucodeFeignException(e.contentUTF8());
        } catch (Exception e) {
            throw new BluecodeSystemException(e);
        }
    }

    @Override
    public ResponseMessageBluecode statusPayment(TransactionMessageBluecode request) throws BluecodeException {
        try {
            return feignBluecodeClient.statusPayment(Collections.singletonMap("merchant_tx_id", request.getTransactionId()));
        } catch (FeignException e) {
            throw new BlucodeFeignException(e.contentUTF8());
        } catch (Exception e) {
            throw new BluecodeSystemException(e);
        }
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
