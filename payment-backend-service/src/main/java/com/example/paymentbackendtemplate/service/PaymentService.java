package com.example.paymentbackendtemplate.service;

import com.example.paymentbackendtemplate.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import template.model.RequestMessage;
import template.model.ResponseMessage;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentService {

    private RequestFacade requestFacade;

    private PaymentRepository paymentRepository;

    public ResponseMessage beginTransaction (RequestMessage request) {
        ResponseMessage responseMessage = requestFacade.startTransaction(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        paymentRepository.save(request);
        return responseMessage;
    }

    public ResponseMessage updatePayment (RequestMessage request) {
        ResponseMessage responseMessage = requestFacade.updatePayment(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        paymentRepository.save(request);
        return responseMessage;
    }

    public RequestMessage getPayment () {
        ResponseMessage responseMessage = requestFacade.statusTransaction(null);
        return null;
    }

    public ResponseMessage capturePayment (RequestMessage request) {
        ResponseMessage responseMessage = requestFacade.captureTransaction(request);
        if(responseMessage == null) {
            throw new IllegalArgumentException("invalid request data or service is not available");
        }
        paymentRepository.save(request);
        return responseMessage;
    }
}
