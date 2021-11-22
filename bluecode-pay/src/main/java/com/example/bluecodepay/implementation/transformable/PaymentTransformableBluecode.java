package com.example.bluecodepay.implementation.transformable;

import com.example.bluecodepay.exception.custom.BluecodeTransformException;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.service.TransformerBluecode;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentTransformable;
import template.model.dto.PaymentDto;

@Component
public class PaymentTransformableBluecode implements PaymentTransformable<RequestMessageBluecode, PaymentDto> {

    private final TransformerBluecode transformServiceBluecode;

    public PaymentTransformableBluecode(TransformerBluecode transformServiceBluecode) {
        this.transformServiceBluecode = transformServiceBluecode;
    }

    @Override
    public RequestMessageBluecode transformDto(PaymentDto dto) throws BluecodeTransformException {
        return transformServiceBluecode.transformRequestMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
