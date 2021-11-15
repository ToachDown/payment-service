package com.example.bluecodepay.interfaceImpl.resolver;

import com.example.bluecodepay.controller.FeignBluecodeClient;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import lombok.Data;
import org.springframework.stereotype.Component;
import template.interfaces.resolver.StartPaymentResolver;

@Component
@Data
public class StartPaymentResolverImpl implements StartPaymentResolver<RequestMessageBluecode, ResponseMessageBluecode> {

    private final FeignBluecodeClient feignBluecodeClient;

    @Override
    public ResponseMessageBluecode startTransaction(RequestMessageBluecode request) {
        return feignBluecodeClient.startPayment(request);
    }

    @Override
    public String getType() {
        return "bluecode";
    }

}
