package com.example.bluecodepay.interfaceImpl.resolver;

import com.example.bluecodepay.controller.FeignBluecodeClient;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import lombok.Data;
import org.springframework.stereotype.Component;
import template.interfaces.resolver.StatusPaymentResolver;

import java.util.Collections;

@Data
@Component
public class StatusPaymentResolverImpl implements StatusPaymentResolver<TransactionMessageBluecode, ResponseMessageBluecode> {

    private FeignBluecodeClient feignBluecodeClient;

    @Override
    public ResponseMessageBluecode statusTransaction(TransactionMessageBluecode request) {
        return feignBluecodeClient.statusPayment(Collections.singletonMap("merchant_tx_id", request.getTransactionId()));
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
