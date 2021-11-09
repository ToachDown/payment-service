package com.example.bluecodepay.service;

import com.example.bluecodepay.controller.FeignBluecodeClient;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import template.interfaces.PaymentResolver;
import template.model.ResponseMessage;

@Getter
@Setter
@Component
public class PaymentResolverImpl implements PaymentResolver<RequestMessageBluecode, ResponseMessageBluecode> {

//    @Autowired
//    private FeignBluecodeClient feignBluecodeClient;

    @Override
    public ResponseMessageBluecode startTransaction(RequestMessageBluecode request) {
//        ResponseMessage responseMessage = feignBluecodeClient.startPayment(request);
        return new ResponseMessageBluecode("OK");
    }

    @Override
    public ResponseMessageBluecode updatePayment(RequestMessageBluecode request) {
//        ResponseMessage responseMessage = feignBluecodeClient.startPayment(request);
        return new ResponseMessageBluecode("PROCESSING");
    }

    @Override
    public ResponseMessageBluecode captureTransaction(RequestMessageBluecode request) {
//        ResponseMessage responseMessage = feignBluecodeClient.capturePayment(request);
        return new ResponseMessageBluecode("CANCEL");
    }

    @Override
    public ResponseMessageBluecode statusTransaction(String transactionId) {
 //       ResponseMessage responseMessage = feignBluecodeClient.statusPayment(transactionId);
        return new ResponseMessageBluecode("STATUS");
    }

    @Override
    public String getType() {
        return "bluecode";
    }
}
