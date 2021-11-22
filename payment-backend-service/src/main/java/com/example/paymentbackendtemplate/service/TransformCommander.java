package com.example.paymentbackendtemplate.service;

import org.springframework.stereotype.Component;
import template.interfaces.PaymentTransformable;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.TransactionMessage;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

import java.util.Map;

@Component
public class TransformCommander {

    private final Map<String, PaymentTransformable<RequestMessage, PaymentDto>> paymentTransformMap;
    private final Map<String, PaymentTransformable<RefundMessage, RefundPaymentDto>> refundTransformMap;
    private final Map<String, PaymentTransformable<TransactionMessage, TransactionDto>> txIdTransformMap;

    @SuppressWarnings("SpringJavaInjectonPointsAutowiringInspection")
    public TransformCommander(Map<String, PaymentTransformable<RequestMessage, PaymentDto>> paymentTransformMap,
                              Map<String, PaymentTransformable<RefundMessage, RefundPaymentDto>> refundTransformMap,
                              Map<String, PaymentTransformable<TransactionMessage, TransactionDto>> txIdTransformMap) {
        this.paymentTransformMap = paymentTransformMap;
        this.refundTransformMap = refundTransformMap;
        this.txIdTransformMap = txIdTransformMap;
    }

    public RefundMessage transformRefundDto(RefundPaymentDto dto) {
        return refundTransformMap.get(dto.getApi()).transformDto(dto);
    }

    public TransactionMessage transformPaymentIdDto(TransactionDto dto) {
        return txIdTransformMap.get(dto.getApi()).transformDto(dto);
    }

    public RequestMessage transformPaymentDto(PaymentDto dto) {
        return paymentTransformMap.get(dto.getApi()).transformDto(dto);
    }
}
