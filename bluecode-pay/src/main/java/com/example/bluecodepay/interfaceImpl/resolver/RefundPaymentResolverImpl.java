package com.example.bluecodepay.interfaceImpl.resolver;

import com.example.bluecodepay.controller.FeignBluecodeClient;
import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import lombok.Data;
import org.springframework.stereotype.Component;
import template.interfaces.resolver.RefundPaymentResolver;

@Data
@Component
public class RefundPaymentResolverImpl implements RefundPaymentResolver<RefundMessageBluecode, ResponseMessageBluecode> {

    private FeignBluecodeClient feignBluecodeClient;

    @Override
    public ResponseMessageBluecode refundPayment(RefundMessageBluecode request) {
        return feignBluecodeClient.refundPayment(request);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
