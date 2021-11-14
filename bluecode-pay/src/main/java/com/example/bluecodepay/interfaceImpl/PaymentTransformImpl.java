package com.example.bluecodepay.interfaceImpl;

import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.service.TransformServiceBluecode;
import lombok.Data;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentTransformable;
import template.model.dto.PaymentDto;

@Data
@Component
public class PaymentTransformImpl implements PaymentTransformable<RequestMessageBluecode, PaymentDto> {

    private final TransformServiceBluecode transformServiceBluecode;

    @Override
    public RequestMessageBluecode transformDto(PaymentDto dto) {
        return transformServiceBluecode.transformRequestMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
