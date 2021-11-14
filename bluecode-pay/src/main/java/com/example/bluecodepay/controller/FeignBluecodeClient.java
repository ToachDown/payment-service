package com.example.bluecodepay.controller;

import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(value = "${bluecode.feign.name}", url = "${bluecode.feign.url}")
public interface FeignBluecodeClient {

    @RequestMapping(method = RequestMethod.POST, value = "payment")
    ResponseMessageBluecode startPayment(RequestMessageBluecode requestMessageBluecode);

    @RequestMapping(method = RequestMethod.POST, value = "cancel")
    ResponseMessageBluecode capturePayment(String merchant_tx_id);

    @RequestMapping(method = RequestMethod.POST, value = "status")
    ResponseMessageBluecode statusPayment(String merchant_tx_id);

    @RequestMapping(method = RequestMethod.POST, value = "refund")
    ResponseMessageBluecode refundPayment();

    @RequestMapping(method = RequestMethod.GET, value = "ping")
    ResponseMessageBluecode heartbeatPayment();
}
