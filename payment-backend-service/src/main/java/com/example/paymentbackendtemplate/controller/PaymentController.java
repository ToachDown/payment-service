package com.example.paymentbackendtemplate.controller;

import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import com.example.paymentbackendtemplate.repository.PaymentRepository;
import com.example.paymentbackendtemplate.service.RequestFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import template.model.RequestMessage;

import java.util.List;


@RestController
@Getter
@Setter
@AllArgsConstructor
@RequestMapping("api")
public class PaymentController {

    private RequestFacade requestFacade;

    private ObjectMapper objectMapper;

    private PaymentRepository paymentRepository;

    @PatchMapping(value = "/update-payment", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public RequestMessage updatePayment(@RequestBody final String jsonString) throws JsonProcessingException {
        RequestMessage requestMessage = objectMapper.readValue(jsonString, RequestMessage.class);
        paymentRepository.save(requestMessage);
        return requestMessage;
    }

    @PostMapping("/capture-payment")
    public void completePayment(@RequestBody ResponseMessageBluecode responseMessage) throws JsonProcessingException {
        String re = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMessage);
        System.out.println(re);
    }

    @ResponseBody
    @GetMapping(value = "/get-all", produces = "application/json")
    public List<RequestMessage> getAllPayments() {
        return paymentRepository.findAll();
    }

    @ResponseBody
    @GetMapping(value = "/get-payment/{id}", produces = "application/json")
    public RequestMessage getPayment(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }

    @PostMapping(value = "/start-payment", produces = "application/json", consumes = "application/json" )
    public List<RequestMessage> startPayment (@RequestBody final RequestMessage requestMessage) {
//        RequestMessage requestMessage = objectMapper.readValue(jsonString, RequestMessage.class);
        paymentRepository.save(requestMessage);
        return paymentRepository.findAll();
    }

    @PostMapping("/cancel")
    public void cancelPayment () {

    }

    @PostMapping("/refund")
    public void refundPayment () {

    }
}
