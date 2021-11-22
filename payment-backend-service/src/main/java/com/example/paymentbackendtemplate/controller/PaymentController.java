package com.example.paymentbackendtemplate.controller;

import com.example.paymentbackendtemplate.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import template.exception.ApiException;
import template.model.ResponseMessage;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@RestController
@Validated
@RequestMapping("api")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ResponseBody
    @PostMapping(value = "/start-payment", produces = "application/json", consumes = "application/json")
    public ResponseMessage startPayment(@RequestBody @Valid @NotNull final PaymentDto init) throws ApiException {
        return paymentService.beginTransaction(init);
    }

    @ResponseBody
    @PatchMapping(value = "/update-payment", consumes = "application/json", produces = "application/json")
    public ResponseMessage updatePayment(@RequestBody @Valid @NotNull final PaymentDto patch) throws ApiException{
        return paymentService.updatePayment(patch);
    }

    @ResponseBody
    @PostMapping(value = "/capture-payment", consumes = "application/json", produces = "application/json")
    public ResponseMessage completePayment(@RequestBody @Valid @NotNull final PaymentDto capture) throws ApiException{
        return paymentService.capturePayment(capture);
    }

    @ResponseBody
    @GetMapping(value = "/{api}/get-payment/{paymentId}", produces = "application/json")
    public ResponseMessage getPayment(@PathVariable("api") @NotNull final String api,
                                      @PathVariable("paymentId") @NotNull final UUID paymentId) throws ApiException {
        return paymentService.getPayment(paymentId, api);
    }

    @ResponseBody
    @PostMapping(value = "/cancel", consumes = "application/json", produces = "application/json")
    public ResponseMessage cancelPayment(@RequestBody @Valid @NotNull final TransactionDto cancel) throws ApiException {
        return paymentService.cancelPayment(cancel);
    }

    @ResponseBody
    @PostMapping(value = "/refund", produces = "application/json", consumes = "application/json")
    public ResponseMessage refundPayment(@RequestBody @Valid @NotNull final RefundPaymentDto refund) throws ApiException {
        return paymentService.refundPayment(refund);
    }
}
