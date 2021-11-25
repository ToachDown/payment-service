package com.example.bluecodepay.service.transformer;

import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.transformer.BluecodeTransformer;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentAwareTransformer;
import template.model.dto.RefundPaymentDto;

@Component
public class BluecodeRefundTransformer implements PaymentAwareTransformer<BluecodeRefundMessage, RefundPaymentDto> {

    private final BluecodeTransformer transformServiceBluecode;

    public BluecodeRefundTransformer(BluecodeTransformer transformServiceBluecode) {
        this.transformServiceBluecode = transformServiceBluecode;
    }

    @Override
    public BluecodeRefundMessage transformDto(RefundPaymentDto dto) {
        return transformServiceBluecode.transformRefundMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
