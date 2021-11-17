package com.example.bluecodepay.service;

import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import template.model.dto.PaymentDto;
import template.model.dto.TransactionDto;
import template.model.dto.RefundPaymentDto;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class TransformerBluecode {

    @Value("${bluecode.tip.amount}")
    private Integer tip;

    public RequestMessageBluecode transformRequestMessage(PaymentDto dto) {
        final String id = dto.getPaymentId() == null ? UUID.randomUUID().toString() : dto.getPaymentId();
        return RequestMessageBluecode.builder()
                .withCurrency(Currency.valueOf(dto.getCurrency()))
                .withType(dto.getApi())
                .withScheme(Scheme.AUTO)
                .withPurchaseAmount(dto.getAmount())
                .withDiscountAmount(dto.getDiscountAmount())
                .withTipAmount(calculatedTipAmount(tip, dto.getAmount()))
                .withRequestedAmount(calculatingClearAmount(dto.getAmount(), tip, dto.getDiscountAmount()))
                .withBarcode("98802222100100123456")
                .withBranchExtId("test")
                .withEndToEndId(dto.isInstantPayment() ? id : null)
                .withMerchantTxId(id)
                .withSlipNote(dto.getDescription())
                .build();
    }

    public RefundMessageBluecode transformRefundMessage(RefundPaymentDto dto) {
        return RefundMessageBluecode.builder()
                .withType("bluecode")
                .withAmount(dto.getAmount())
                .withAcquirerTransactionId(dto.getAcquirerId())
                .withReason(dto.getReason())
                .build();
    }

    public TransactionMessageBluecode transformTransactionMessage(TransactionDto dto) {
        return TransactionMessageBluecode.builder()
                .withType(dto.getApi())
                .withTransactionId(dto.getPaymentId())
                .build();
    }

    private int calculatedTipAmount (int tip, int full) {
        return (full*tip)/100;
    }

    private int calculatingClearAmount (int full, int tip, int discount) {
        return (full - discount) + (int) ((full - discount) * ( tip/((float) 100)));
    }
}
