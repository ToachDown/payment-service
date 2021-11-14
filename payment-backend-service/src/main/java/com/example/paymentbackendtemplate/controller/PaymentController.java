package com.example.paymentbackendtemplate.controller;

import com.example.paymentbackendtemplate.repository.PaymentRepository;
import com.example.paymentbackendtemplate.service.PaymentService;
import com.example.paymentbackendtemplate.service.TransformCommander;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TxIdMessage;
import template.model.dto.PaymentDto;
import template.model.dto.PaymentIdDto;
import template.model.dto.RefundPaymentDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@Getter
@Setter
@AllArgsConstructor
@Validated
@RequestMapping("api")
public class PaymentController {

    private final TransformCommander transformCommander;

    private final PaymentService paymentService;

    private final ObjectMapper objectMapper;

    private final PaymentRepository paymentRepository;

    @ResponseBody
    @PostMapping(value = "/start-payment", produces = "application/json", consumes = "application/json" )
    public ResponseMessage startPayment (@RequestBody @Valid @NotNull final PaymentDto init) {
        final RequestMessage requestMessage = transformCommander.transformPaymentDto(init);
        return paymentService.beginTransaction(requestMessage);
    }

    @ResponseBody
    @PatchMapping(value = "/update-payment", consumes = "application/json", produces = "application/json")
    public ResponseMessage updatePayment(@RequestBody final PaymentDto patch){
        final RequestMessage requestMessage = transformCommander.transformPaymentDto(patch);
        return paymentService.updatePayment(requestMessage);
    }

    @ResponseBody
    @PostMapping(value = "/capture-payment", consumes = "application/json", produces = "application/json")
    public void completePayment(@RequestBody @Valid @NotNull final PaymentDto payment) {


    }

    @ResponseBody
    @GetMapping(value = "/get-payment", produces = "application/json")
    public RequestMessage getPayment(@RequestBody @Valid @NotNull final PaymentIdDto txIdDto) {
        final TxIdMessage txIdMessage = transformCommander.transformPaymentIdDto(txIdDto);
        return null;
    }

    @PostMapping("/cancel")
    public void cancelPayment (@RequestBody @Valid @NotNull final PaymentIdDto txIdDto) {
        final TxIdMessage txIdMessage = transformCommander.transformPaymentIdDto(txIdDto);
    }

    @PostMapping("/refund")
    public ResponseMessage refundPayment (@RequestBody @Valid @NotNull final RefundPaymentDto refund) {
        final RefundMessage refundMessage = transformCommander.transformRefundDto(refund);
        return null;
    }
}
