package com.example.bluecodepay.service.resolver;

import com.example.backendtemplate.interfaces.PaymentResolver;
import com.example.bluecodepay.annotation.FeignHandler;
import com.example.bluecodepay.client.BluecodeFeignClient;
import com.example.bluecodepay.exception.custom.BluecodeFeignUnsupportedMethodException;
import com.example.bluecodepay.factory.Resilience4jFactory;
import com.example.bluecodepay.model.enums.Result;
import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.request.BluecodeTransactionMessage;
import com.example.bluecodepay.model.response.BluecodeResponseMessage;
import com.example.bluecodepay.model.response.BluecodeResponseMessageProcessing;
import feign.FeignException;
import io.github.resilience4j.retry.Retry;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class BluecodePaymentResolver implements PaymentResolver<BluecodeRequestMessage, BluecodeRefundMessage, BluecodeTransactionMessage, BluecodeResponseMessage> {

    private static final String PAYMENT_API_NOT_SUPPORT_OPERATION = "this payment api not support this operation";

    private final BluecodeFeignClient bluecodeFeignClient;

    private final Resilience4jFactory resilience4jFactory;


    public BluecodePaymentResolver(final BluecodeFeignClient bluecodeFeignClient,
                                   final Resilience4jFactory resilience4jFactory) {
        this.bluecodeFeignClient = bluecodeFeignClient;
        this.resilience4jFactory = resilience4jFactory;
    }

    @Override
    public BluecodeResponseMessage capturePayment(BluecodeRequestMessage request) {
        throw new BluecodeFeignUnsupportedMethodException(PAYMENT_API_NOT_SUPPORT_OPERATION);
    }

    @Override
    @FeignHandler(exceptions = FeignException.class)
    public BluecodeResponseMessage startPayment(BluecodeRequestMessage request) {
        final BluecodeResponseMessage responseMessageBluecode = bluecodeFeignClient.startPayment(request);
        if (responseMessageBluecode.getResult().equals(Result.PROCESSING)) {
            BluecodeResponseMessageProcessing bluecodeResponseProcessing = (BluecodeResponseMessageProcessing) responseMessageBluecode;
            Retry retry = resilience4jFactory.getConfigureRetry(bluecodeResponseProcessing.getStatus());
            Supplier<BluecodeResponseMessage> supplier = () -> bluecodeFeignClient.statusPayment(Map.of("merchant_tx_id", request.getId()));
            return Retry.decorateSupplier(retry, supplier).get();
        }
        return responseMessageBluecode;
    }

    @Override
    public BluecodeResponseMessage updatePayment(BluecodeRequestMessage request) {
        throw new BluecodeFeignUnsupportedMethodException(PAYMENT_API_NOT_SUPPORT_OPERATION);
    }

    @Override
    @FeignHandler(exceptions = FeignException.class)
    public BluecodeResponseMessage refundPayment(BluecodeRefundMessage request) {
        return bluecodeFeignClient.refundPayment(request);
    }

    @Override
    @FeignHandler(exceptions = FeignException.class)
    public BluecodeResponseMessage cancelPayment(BluecodeTransactionMessage request) {
        return bluecodeFeignClient.cancelPayment(Collections.singletonMap("merchant_tx_id", request.getTransactionId()));
    }

    @Override
    @FeignHandler(exceptions = FeignException.class)
    public BluecodeResponseMessage statusPayment(BluecodeTransactionMessage request) {
        return bluecodeFeignClient.statusPayment(Collections.singletonMap("merchant_tx_id", request.getTransactionId()));
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
