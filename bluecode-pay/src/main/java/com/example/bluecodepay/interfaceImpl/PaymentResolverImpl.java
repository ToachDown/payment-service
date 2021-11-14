package com.example.bluecodepay.interfaceImpl;

import com.example.bluecodepay.controller.FeignBluecodeClient;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentResolver;
import template.model.ResponseMessage;

@Component
public class PaymentResolverImpl implements PaymentResolver<RequestMessageBluecode, ResponseMessageBluecode> {

    private final FeignBluecodeClient feignBluecodeClient;

    @Override
    public ResponseMessageBluecode startTransaction(RequestMessageBluecode request) {
        return feignBluecodeClient.startPayment(request);
    }

    @Override
    public ResponseMessageBluecode updatePayment(RequestMessageBluecode request) {
        return feignBluecodeClient.startPayment(request);
    }

    @Override
    public ResponseMessageBluecode captureTransaction(RequestMessageBluecode request) {
        return feignBluecodeClient.capturePayment(request.getMerchantTxId());
    }

    @Override
    public String getType() {
        return "bluecode";
    }

    public PaymentResolverImpl(FeignBluecodeClient feignBluecodeClient) {
        this.feignBluecodeClient = feignBluecodeClient;
    }
}
