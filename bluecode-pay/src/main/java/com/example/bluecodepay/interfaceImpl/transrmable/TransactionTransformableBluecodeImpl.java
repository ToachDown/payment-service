package com.example.bluecodepay.interfaceImpl.transrmable;

import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import com.example.bluecodepay.service.TransformServiceBluecode;
import lombok.Data;
import org.springframework.stereotype.Component;
import template.interfaces.transformable.PaymentTransformable;
import template.model.dto.TransactionDto;

@Data
@Component
public class TransactionTransformableBluecodeImpl implements PaymentTransformable<TransactionMessageBluecode, TransactionDto> {

    private TransformServiceBluecode transformServiceBluecode;

    @Override
    public TransactionMessageBluecode transformDto(TransactionDto dto) {
        return transformServiceBluecode.transformTransactionMessage(dto);
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
