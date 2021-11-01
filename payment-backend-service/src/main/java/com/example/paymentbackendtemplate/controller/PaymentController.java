package com.example.paymentbackendtemplate.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class PaymentController {

    @PostMapping("/start-payment")
    public void startPayment() {

    }

    @PatchMapping("/update-payment")
    public void updatePayment() {

    }

    @PostMapping("/capture-payment")
    public void completePayment() {

    }

    @GetMapping("/get-payment")
    public void getCurrentPayment () {

    }

    @PostMapping("/cancel")
    public void cancelPayment () {

    }

    @PostMapping("/refund")
    public void refundPayment () {

    }
}
