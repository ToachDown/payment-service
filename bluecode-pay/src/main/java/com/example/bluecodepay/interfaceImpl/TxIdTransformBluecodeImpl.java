package com.example.bluecodepay.interfaceImpl;

import com.example.bluecodepay.model.request.TxIdMessageBluecode;
import com.example.bluecodepay.service.TransformServiceBluecode;
import lombok.Data;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentTransformable;
import template.model.dto.PaymentIdDto;

@Data
@Component
public class TxIdTransformBluecodeImpl implements PaymentTransformable<TxIdMessageBluecode, PaymentIdDto> {

    private TransformServiceBluecode transformServiceBluecode;

    @Override
    public TxIdMessageBluecode transformDto(PaymentIdDto dto) {
        return transformServiceBluecode.transformTxIdMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
