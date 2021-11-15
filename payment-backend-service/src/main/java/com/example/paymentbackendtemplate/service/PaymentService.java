package com.example.paymentbackendtemplate.service;

import com.example.paymentbackendtemplate.repository.PaymentRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;

@Service
@Data
public class PaymentService {

    private final RequestCommander requestCommander;

    private final PaymentRepository paymentRepository;

    public ResponseMessage beginTransaction (RequestMessage request) {
        ResponseMessage responseMessage = requestCommander.startTransaction(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        paymentRepository.save(request);
        return responseMessage;
    }

    public ResponseMessage updatePayment (RequestMessage request) {
        ResponseMessage responseMessage = requestCommander.updatePayment(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        paymentRepository.saveAndFlush(request);
        return responseMessage;
    }

    public ResponseMessage refundPayment(RefundMessage request) {
        ResponseMessage responseMessage = requestCommander.refundPayment(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        return responseMessage;
    }

    public ResponseMessage getPayment (TransactionMessage request) {
        ResponseMessage responseMessage = requestCommander.statusTransaction(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        return responseMessage;
    }

    public ResponseMessage cancelPayment (TransactionMessage request) {
        ResponseMessage responseMessage = requestCommander.cancelTransaction(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        return responseMessage;
    }

    public ResponseMessage capturePayment (RequestMessage request) {
        ResponseMessage responseMessage = requestCommander.captureTransaction(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        paymentRepository.save(request);
        return responseMessage;
    }

}
