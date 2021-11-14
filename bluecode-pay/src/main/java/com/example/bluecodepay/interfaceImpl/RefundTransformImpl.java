package com.example.bluecodepay.interfaceImpl;

import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.service.TransformServiceBluecode;
import lombok.Data;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentTransformable;
import template.model.dto.RefundPaymentDto;

@Data
@Component
public class RefundTransformImpl implements PaymentTransformable<RefundMessageBluecode, RefundPaymentDto> {

    private TransformServiceBluecode transformServiceBluecode;

    @Override
    public RefundMessageBluecode transformDto(RefundPaymentDto dto) {
        return transformServiceBluecode.transformRefundMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
