package com.example.paymentbackendtemplate.service;

import com.example.paymentbackendtemplate.repository.PaymentRepository;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.stereotype.Service;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

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

    public ResponseMessage beginTransaction (PaymentDto dto) {
        final RequestMessage request = transformCommander.transformPaymentDto(dto);
        final ResponseMessage response = requestCommander.startTransaction(request);
        paymentRepository.save(request);
        return response;
    }

    public ResponseMessage updatePayment (PaymentDto dto) {
        final RequestMessage request = transformCommander.transformPaymentDto(dto);
        final ResponseMessage response = requestCommander.updatePayment(request);
        paymentRepository.saveAndFlush(request);
        return response;
    }

    public ResponseMessage refundPayment(RefundPaymentDto dto) {
        final RefundMessage request = transformCommander.transformRefundDto(dto);
        final ResponseMessage response = requestCommander.refundPayment(request);
        return response;
    }

    public ResponseMessage getPayment (String paymentId, String api) {
        final TransactionDto dto = TransactionDto.builder()
                .withApi(api)
                .withPaymentId(paymentId)
                .build();
        final TransactionMessage request = transformCommander.transformPaymentIdDto(dto);
        final ResponseMessage response = requestCommander.statusTransaction(request);
        return response;
    }

    public ResponseMessage cancelPayment (TransactionDto dto) {
        final TransactionMessage request = transformCommander.transformPaymentIdDto(dto);
        final ResponseMessage response = requestCommander.cancelTransaction(request);
        return response;
    }

    public ResponseMessage capturePayment (PaymentDto dto) {
        final RequestMessage request = transformCommander.transformPaymentDto(dto);
        final ResponseMessage response = requestCommander.captureTransaction(request);
        paymentRepository.saveAndFlush(request);
        return response;
    }

}
