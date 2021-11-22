package com.example.paymentbackendtemplate.service;

import com.example.paymentbackendtemplate.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

import java.util.UUID;

@Service
public class PaymentService {

    private final RequestCommander requestCommander;

    private final TransformCommander transformCommander;

    private final PaymentRepository paymentRepository;

    public PaymentService(RequestCommander requestCommander,
                          TransformCommander transformCommander,
                          PaymentRepository paymentRepository) {
        this.requestCommander = requestCommander;
        this.transformCommander = transformCommander;
        this.paymentRepository = paymentRepository;
    }

    public ResponseMessage beginTransaction(PaymentDto dto) {
        RequestMessage request = transformCommander.transformPaymentDto(dto);
        request = paymentRepository.saveAndFlush(request);
        ResponseMessage response = requestCommander.startTransaction(request);
        request = requestCommander.changePaymentStateWithResponse(request, response);
        paymentRepository.save(request);
        return response;
    }

    public ResponseMessage updatePayment(PaymentDto dto) {
        RequestMessage request = transformCommander.transformPaymentDto(dto);
        request = paymentRepository.saveAndFlush(request);
        final ResponseMessage response = requestCommander.updatePayment(request);
        request = requestCommander.changePaymentStateWithResponse(request, response);
        paymentRepository.save(request);
        return response;
    }

    public ResponseMessage refundPayment(RefundPaymentDto dto) {
        final RefundMessage refundRequest = transformCommander.transformRefundDto(dto);
        RequestMessage request = paymentRepository.findById(dto.getTransactionId()).get();
        final ResponseMessage response = requestCommander.refundPayment(refundRequest);
        request = requestCommander.changePaymentState(request, "REFUNDED");
        paymentRepository.save(request);
        return response;
    }

    public ResponseMessage getPayment(UUID paymentId, String api) {
        final TransactionDto dto = TransactionDto.builder()
                .withApi(api)
                .withPaymentId(paymentId)
                .build();
        final TransactionMessage request = transformCommander.transformPaymentIdDto(dto);
        final ResponseMessage response = requestCommander.statusTransaction(request);
        return response;
    }

    public ResponseMessage cancelPayment(TransactionDto dto) {
        final TransactionMessage txRequest = transformCommander.transformPaymentIdDto(dto);
        RequestMessage request = paymentRepository.getById(dto.getPaymentId());
        final ResponseMessage response = requestCommander.cancelTransaction(txRequest);
        request = requestCommander.changePaymentState(request, "CANCELLED");
        paymentRepository.save(request);
        return response;
    }

    public ResponseMessage capturePayment(PaymentDto dto) {
        RequestMessage request = transformCommander.transformPaymentDto(dto);
        request = paymentRepository.saveAndFlush(request);
        final ResponseMessage response = requestCommander.captureTransaction(request);
        request = requestCommander.changePaymentStateWithResponse(request, response);
        paymentRepository.save(request);
        return response;
    }

}
