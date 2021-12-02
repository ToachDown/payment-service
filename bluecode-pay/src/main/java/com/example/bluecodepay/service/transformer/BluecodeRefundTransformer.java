package com.example.bluecodepay.service.transformer;

import com.example.backendtemplate.interfaces.PaymentAwareTransformer;
import com.example.backendtemplate.model.dto.RefundPaymentDto;
import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.transformer.BluecodeTransformer;
import org.springframework.stereotype.Component;

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
