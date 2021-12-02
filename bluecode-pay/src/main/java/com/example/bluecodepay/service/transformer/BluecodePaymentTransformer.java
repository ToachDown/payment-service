package com.example.bluecodepay.service.transformer;

import com.example.backendtemplate.interfaces.PaymentAwareTransformer;
import com.example.backendtemplate.model.dto.PaymentDto;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.transformer.BluecodeTransformer;
import org.springframework.stereotype.Component;

@Component
public class BluecodePaymentTransformer implements PaymentAwareTransformer<BluecodeRequestMessage, PaymentDto> {

    private final BluecodeTransformer transformServiceBluecode;

    public BluecodePaymentTransformer(BluecodeTransformer transformServiceBluecode) {
        this.transformServiceBluecode = transformServiceBluecode;
    }

    @Override
    public BluecodeRequestMessage transformDto(PaymentDto dto) {
        return transformServiceBluecode.transformRequestMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
