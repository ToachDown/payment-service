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
import template.model.TransactionMessage;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

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
    public ResponseMessage completePayment(@RequestBody @Valid @NotNull final PaymentDto payment) {
        final RequestMessage requestMessage = transformCommander.transformPaymentDto(payment);
        return paymentService.capturePayment(requestMessage);
    }

    @ResponseBody
    @GetMapping(value = "/get-payment", produces = "application/json")
    public ResponseMessage getPayment(@RequestBody @Valid @NotNull final TransactionDto txIdDto) {
        final TransactionMessage transactionMessage = transformCommander.transformPaymentIdDto(txIdDto);
        return paymentService.getPayment(transactionMessage);
    }

    @PostMapping("/cancel")
    public ResponseMessage cancelPayment (@RequestBody @Valid @NotNull final TransactionDto txIdDto) {
        final TransactionMessage transactionMessage = transformCommander.transformPaymentIdDto(txIdDto);
        return paymentService.cancelPayment(transactionMessage);
    }

    @PostMapping("/refund")
    public ResponseMessage refundPayment (@RequestBody @Valid @NotNull final RefundPaymentDto refund) {
        final RefundMessage refundMessage = transformCommander.transformRefundDto(refund);
        return paymentService.refundPayment(refundMessage);
    }
}
