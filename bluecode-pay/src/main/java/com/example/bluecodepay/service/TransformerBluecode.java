package com.example.bluecodepay.service;

import com.example.bluecodepay.exception.custom.BluecodeTransformException;
import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.example.bluecodepay.model.enums.State;
import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

import java.util.UUID;

@Service
public class TransformerBluecode {

    private static final String SUCCESS_BARCODE = "98802222100100123456";
    private static final String SUCCESS_REFUND_BARCODE = "98800000000000000099";
    private static final String PROCESSING_BARCODE = "98802222999900308001";
    private static final String ERROR_BARCODE = "98804444000000402005";
    private static final String ERROR_INTERNAL_SERVER = "98802222999900500500";

    @Value("${bluecode.tip.amount}")
    private Integer tip;

    public RequestMessageBluecode transformRequestMessage(PaymentDto dto) throws BluecodeTransformException {
        try {
            final String id = UUID.randomUUID().toString();
            return RequestMessageBluecode.builder()
                    .withCurrency(Currency.valueOf(dto.getCurrency()))
                    .withType(dto.getApi())
                    .withState(State.TODO)
                    .withScheme(Scheme.AUTO)
                    .withPurchaseAmount(dto.getAmount())
                    .withDiscountAmount(dto.getDiscountAmount())
                    .withTipAmount(calculatedTipAmount(tip, dto.getAmount()))
                    .withRequestedAmount(calculatingClearAmount(dto.getAmount(), tip, dto.getDiscountAmount()))
                    .withBarcode(ERROR_BARCODE)
                    .withBranchExtId("test")
                    .withEndToEndId(dto.isInstantPayment() ? id : null)
                    .withSlipNote(dto.getDescription())
                    .build();
        } catch (RuntimeException e) {
            throw new BluecodeTransformException(e);
        }
    }

    public RefundMessageBluecode transformRefundMessage(RefundPaymentDto dto) throws BluecodeTransformException {
        try {
            return RefundMessageBluecode.builder()
                    .withType("bluecode")
                    .withAmount(dto.getAmount())
                    .withAcquirerTransactionId(dto.getAcquirerId())
                    .withReason(dto.getReason())
                    .build();
        } catch (RuntimeException e) {
            throw new BluecodeTransformException(e);
        }
    }

    public TransactionMessageBluecode transformTransactionMessage(TransactionDto dto) throws BluecodeTransformException {
        try {
            return TransactionMessageBluecode.builder()
                    .withType(dto.getApi())
                    .withTransactionId(dto.getPaymentId())
                    .build();
        } catch (RuntimeException e) {
            throw new BluecodeTransformException(e);
        }
    }

    private int calculatedTipAmount(int tip, int full) {
        return (full * tip) / 100;
    }

    private int calculatingClearAmount(int full, int tip, int discount) {
        return (full - discount) + (int) ((full - discount) * (tip / ((float) 100)));
    }
}
