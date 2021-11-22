package com.example.bluecodepay.implementation.transformable;

import com.example.bluecodepay.exception.custom.BluecodeTransformException;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import com.example.bluecodepay.service.TransformerBluecode;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentTransformable;
import template.model.dto.TransactionDto;

@Component
public class TransactionTransformableBluecode implements PaymentTransformable<TransactionMessageBluecode, TransactionDto> {

    private final TransformerBluecode transformServiceBluecode;

    public TransactionTransformableBluecode(TransformerBluecode transformServiceBluecode) {
        this.transformServiceBluecode = transformServiceBluecode;
    }

    @Override
    public TransactionMessageBluecode transformDto(TransactionDto dto) throws BluecodeTransformException {
        return transformServiceBluecode.transformTransactionMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
