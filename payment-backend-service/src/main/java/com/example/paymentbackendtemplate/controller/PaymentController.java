package com.example.paymentbackendtemplate.controller;

import com.example.bluecodepay.model.RequestMessageBluecode;
import com.example.paymentbackendtemplate.service.RequestFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import template.model.RequestMessage;
import template.model.ResponseMessage;

@RestController
@Getter
@Setter
@RequestMapping("api")
public class PaymentController {

    @Autowired
    private RequestFacade requestFacade;

    @PostMapping("/start-payment")
    public void startPayment() {

    }

    @PatchMapping("/update-payment")
    public void updatePayment() {

    }

    @PostMapping("/capture-payment")
    public void completePayment() {

    }

    @PostMapping(value = "/get-payment", produces = "application/json", consumes = "application/json" )
    public ResponseMessage getCurrentPayment (@RequestBody RequestMessage requestMessage) {
        return requestFacade.getAnswer(new RequestMessageBluecode(requestMessage.getType()));
    }

    @PostMapping("/cancel")
    public void cancelPayment () {

    }

    @PostMapping("/refund")
    public void refundPayment () {

    }
}
