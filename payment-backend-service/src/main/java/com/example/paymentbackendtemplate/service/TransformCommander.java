package com.example.paymentbackendtemplate.service;

import com.example.backendtemplate.exception.ApiFeignException;
import com.example.backendtemplate.interfaces.PaymentAwareTransformer;
import com.example.backendtemplate.model.RefundMessage;
import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.TransactionMessage;
import com.example.backendtemplate.model.dto.PaymentDto;
import com.example.backendtemplate.model.dto.RefundPaymentDto;
import com.example.backendtemplate.model.dto.TransactionDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransformCommander {

    private final Map<String, PaymentAwareTransformer<RequestMessage, PaymentDto>> paymentTransformMap;
    private final Map<String, PaymentAwareTransformer<RefundMessage, RefundPaymentDto>> refundTransformMap;
    private final Map<String, PaymentAwareTransformer<TransactionMessage, TransactionDto>> txIdTransformMap;

    @SuppressWarnings("SpringJavaInjectonPointsAutowiringInspection")
    public TransformCommander(Map<String, PaymentAwareTransformer<RequestMessage, PaymentDto>> paymentTransformMap,
                              Map<String, PaymentAwareTransformer<RefundMessage, RefundPaymentDto>> refundTransformMap,
                              Map<String, PaymentAwareTransformer<TransactionMessage, TransactionDto>> txIdTransformMap) {
        this.paymentTransformMap = paymentTransformMap;
        this.refundTransformMap = refundTransformMap;
        this.txIdTransformMap = txIdTransformMap;
    }

    public RefundMessage transformRefundDto(RefundPaymentDto dto) throws ApiFeignException {
        return refundTransformMap.get(dto.getApi()).transformDto(dto);
    }

    public TransactionMessage transformPaymentIdDto(TransactionDto dto) throws ApiFeignException {
        return txIdTransformMap.get(dto.getApi()).transformDto(dto);
    }

    public RequestMessage transformPaymentDto(PaymentDto dto) throws ApiFeignException {
        return paymentTransformMap.get(dto.getApi()).transformDto(dto);
    }
}
