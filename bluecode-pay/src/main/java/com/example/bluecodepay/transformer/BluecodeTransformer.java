package com.example.bluecodepay.transformer;

import com.example.bluecodepay.exception.custom.BluecodeFeignBadRequestException;
import com.example.bluecodepay.exception.custom.BluecodeTransformBadParametersException;
import com.example.bluecodepay.model.enums.Barcode;
import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.example.bluecodepay.model.enums.State;
import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.request.BluecodeTransactionMessage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

import java.util.UUID;

@Service
@Setter
public class BluecodeTransformer {

    private static final String BAD_DISCOUNT_WITH_AMOUNT = "Bad request: discount > amount";

    @Value("${bluecode.tip.amount}")
    private Integer tip;

    public BluecodeRequestMessage transformRequestMessage(PaymentDto dto) {
            return BluecodeRequestMessage.builder()
                    .withCurrency(Currency.valueOf(dto.getCurrency()))
                    .withType(dto.getApi())
                    .withState(State.TODO)
                    .withScheme(Scheme.AUTO)
                    .withPurchaseAmount(dto.getAmount())
                    .withDiscountAmount(dto.getDiscountAmount())
                    .withTipAmount(calculatedTipAmount(tip, dto.getAmount(), dto.getDiscountAmount()))
                    .withRequestedAmount(calculatingClearAmount(dto.getAmount(), tip, dto.getDiscountAmount()))
                    .withBarcode(Barcode.valueOf(dto.getBarcode()).getBarcode())
                    .withBranchExtId("test")
                    .withEndToEndId(dto.isInstantPayment() ? UUID.randomUUID().toString() : null)
                    .withSlipNote(dto.getDescription())
                    .build();
    }

    public BluecodeRefundMessage transformRefundMessage(RefundPaymentDto dto) {
        return BluecodeRefundMessage.builder()
                    .withType("bluecode")
                    .withAmount(dto.getAmount())
                    .withAcquirerTransactionId(dto.getAcquirerId())
                    .withReason(dto.getReason())
                    .build();
    }

    public BluecodeTransactionMessage transformTransactionMessage(TransactionDto dto) {
        return BluecodeTransactionMessage.builder()
                    .withType(dto.getApi())
                    .withTransactionId(dto.getPaymentId())
                    .build();
    }

    public int calculatedTipAmount(int tip, int full, int discount) {
        if(discount > full) {
            throw new BluecodeTransformBadParametersException(BAD_DISCOUNT_WITH_AMOUNT);
        }
        return ((full-discount) * tip) / 100;
    }

    public int calculatingClearAmount(int full, int tip, int discount) throws BluecodeFeignBadRequestException {
        int result = (full - discount) + (int) ((full - discount) * (tip / ((float) 100)));
        if(discount > full) {
            throw new BluecodeTransformBadParametersException(BAD_DISCOUNT_WITH_AMOUNT);
        }
        return result;
    }
}
