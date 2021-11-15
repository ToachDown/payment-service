package com.example.bluecodepay.interfaceImpl.resolver;

import com.example.bluecodepay.controller.FeignBluecodeClient;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import lombok.Data;
import org.springframework.stereotype.Component;
import template.interfaces.resolver.CancelPaymentResolver;
import template.interfaces.resolver.StatusPaymentResolver;

import java.util.Collections;

@Data
@Component
public class CancelPaymentResolverImpl implements CancelPaymentResolver<TransactionMessageBluecode, ResponseMessageBluecode> {

    private final FeignBluecodeClient feignBluecodeClient;

    @Override
    public ResponseMessageBluecode cancelTransaction(TransactionMessageBluecode request) {
        return feignBluecodeClient.cancelPayment(Collections.singletonMap("merchant_tx_id", request.getTransactionId()));
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
