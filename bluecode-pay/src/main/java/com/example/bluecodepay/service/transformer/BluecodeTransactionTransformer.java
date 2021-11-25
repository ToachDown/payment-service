package com.example.bluecodepay.service.transformer;

import com.example.bluecodepay.model.request.BluecodeTransactionMessage;
import com.example.bluecodepay.transformer.BluecodeTransformer;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentAwareTransformer;
import template.model.dto.TransactionDto;

@Component
public class BluecodeTransactionTransformer implements PaymentAwareTransformer<BluecodeTransactionMessage, TransactionDto> {

    private final BluecodeTransformer transformServiceBluecode;

    public BluecodeTransactionTransformer(BluecodeTransformer transformServiceBluecode) {
        this.transformServiceBluecode = transformServiceBluecode;
    }

    @Override
    public BluecodeTransactionMessage transformDto(TransactionDto dto) {
        return transformServiceBluecode.transformTransactionMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
