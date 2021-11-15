package com.example.bluecodepay.controller;

import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Service
@FeignClient(value = "${bluecode.feign.name}", url = "${bluecode.feign.url}")
public interface FeignBluecodeClient {

    @RequestMapping(method = RequestMethod.POST, value = "payment")
    ResponseMessageBluecode startPayment(RequestMessageBluecode requestMessageBluecode);

    @RequestMapping(method = RequestMethod.POST, value = "cancel")
    ResponseMessageBluecode cancelPayment(Map<String, String> merchantIdMap);

    @RequestMapping(method = RequestMethod.POST, value = "status")
    ResponseMessageBluecode statusPayment(Map<String, String> merchantIdMap);

    @RequestMapping(method = RequestMethod.POST, value = "refund")
    ResponseMessageBluecode refundPayment(RefundMessageBluecode request);

    @RequestMapping(method = RequestMethod.GET, value = "ping")
    ResponseMessageBluecode pingPayment();
}
