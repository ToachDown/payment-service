package com.example.paymentbackendtemplate.controller;

import com.example.backendtemplate.exception.ApiFeignException;
import com.example.backendtemplate.model.ResponseMessage;
import com.example.backendtemplate.model.dto.PaymentDto;
import com.example.backendtemplate.model.dto.RefundPaymentDto;
import com.example.backendtemplate.model.dto.TransactionDto;
import com.example.paymentbackendtemplate.exception.custom.DataBaseNotFoundException;
import com.example.paymentbackendtemplate.service.PaymentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseMessage startPayment(@RequestBody @Valid @NotNull final PaymentDto init) throws ApiFeignException {
        return paymentService.beginTransaction(init);
    }

    @ResponseBody
    @PatchMapping(value = "/update-payment", consumes = "application/json", produces = "application/json")
    public ResponseMessage updatePayment(@RequestBody @Valid @NotNull final PaymentDto patch) throws ApiFeignException {
        return paymentService.updatePayment(patch);
    }

    @ResponseBody
    @PostMapping(value = "/capture-payment", consumes = "application/json", produces = "application/json")
    public ResponseMessage completePayment(@RequestBody @Valid @NotNull final PaymentDto capture) throws ApiFeignException {
        return paymentService.capturePayment(capture);
    }

    @ResponseBody
    @GetMapping(value = "/{api}/get-payment/{paymentId}", produces = "application/json")
    public ResponseMessage getPayment(@PathVariable("api") @NotNull final String api,
                                      @PathVariable("paymentId") @NotNull final UUID paymentId) throws ApiFeignException {
        return paymentService.getPayment(paymentId, api);
    }

    @ResponseBody
    @PostMapping(value = "/cancel", consumes = "application/json", produces = "application/json")
    public ResponseMessage cancelPayment(@RequestBody @Valid @NotNull final TransactionDto cancel) throws ApiFeignException, DataBaseNotFoundException {
        return paymentService.cancelPayment(cancel);
    }

    @ResponseBody
    @PostMapping(value = "/refund", produces = "application/json", consumes = "application/json")
    public ResponseMessage refundPayment(@RequestBody @Valid @NotNull final RefundPaymentDto refund) throws ApiFeignException, DataBaseNotFoundException {
        return paymentService.refundPayment(refund);
    }
}
