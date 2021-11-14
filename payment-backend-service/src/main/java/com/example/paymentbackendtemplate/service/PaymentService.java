package com.example.paymentbackendtemplate.service;

import com.example.paymentbackendtemplate.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import template.model.RequestMessage;
import template.model.ResponseMessage;

@Service
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

    public RequestMessage getPayment () {
        ResponseMessage responseMessage = requestCommander.statusTransaction(null);
        return null;
    }

    public ResponseMessage capturePayment (RequestMessage request) {
        ResponseMessage responseMessage = requestCommander.captureTransaction(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        paymentRepository.save(request);
        return responseMessage;
    }

    public PaymentService(RequestCommander requestCommander, PaymentRepository paymentRepository) {
        this.requestCommander = requestCommander;
        this.paymentRepository = paymentRepository;
    }
}
