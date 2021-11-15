package com.example.bluecodepay.service;

import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.request.TransactionMessageBluecode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import template.model.dto.PaymentDto;
import template.model.dto.TransactionDto;
import template.model.dto.RefundPaymentDto;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class TransformServiceBluecode {

    @Value("${bluecode.tip.amount}")
    private Integer tip;

    public RequestMessageBluecode transformRequestMessage(PaymentDto dto) {
        final RequestMessageBluecode request = new RequestMessageBluecode();
        final String id = dto.getTransactionId() == null ? UUID.randomUUID().toString() : dto.getTransactionId();
        request.setCurrency(Currency.valueOf(dto.getCurrency()));
        request.setType(dto.getApi());
        request.setPurchaseAmount(dto.getAmount());
        request.setDiscountAmount(dto.getDiscountAmount());
        request.setTipAmount(tip);
        request.setRequestedAmount(calculatingClearAmount(dto.getAmount(), tip, dto.getDiscountAmount()));
        request.setBarcode("bluecode");
        request.setBranchExtId(UUID.nameUUIDFromBytes(dto.getApi().getBytes(StandardCharsets.UTF_8)).toString());
        request.setEndToEndId(dto.isInstantPayment() ? id : null);
        request.setMerchantTxId(id);
        request.setSlipNote(dto.getDescription());
        return request;
    }

    public RefundMessageBluecode transformRefundMessage(RefundPaymentDto dto) {
        final RefundMessageBluecode refund = new RefundMessageBluecode();
        refund.setType("bluecode");
        refund.setAmount(dto.getAmount());
        refund.setAcquirerTransactionId(dto.getAcquirerTransactionId());
        refund.setReason(dto.getReason());
        return refund;
    }

    public TransactionMessageBluecode transformTransactionMessage(TransactionDto dto) {
        final TransactionMessageBluecode txIdMessage = new TransactionMessageBluecode();
        txIdMessage.setType(dto.getApi());
        txIdMessage.setTransactionId(dto.getTransactionId());
        return txIdMessage;
    }

    private int calculatingClearAmount (int full, int tip, int discount) {
        return (full - discount) + (int) ((full - discount) * ( tip/((float) 100)));
    }
}
