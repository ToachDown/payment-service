package com.example.bluecodepay.implementation.transformable;

import com.example.bluecodepay.exception.custom.BluecodeTransformException;
import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.service.TransformerBluecode;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentTransformable;
import template.model.dto.RefundPaymentDto;

@Component
public class RefundTransformableBluecode implements PaymentTransformable<RefundMessageBluecode, RefundPaymentDto> {

    private final TransformerBluecode transformServiceBluecode;

    public RefundTransformableBluecode(TransformerBluecode transformServiceBluecode) {
        this.transformServiceBluecode = transformServiceBluecode;
    }

    @Override
    public RefundMessageBluecode transformDto(RefundPaymentDto dto) throws BluecodeTransformException {
        return transformServiceBluecode.transformRefundMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
