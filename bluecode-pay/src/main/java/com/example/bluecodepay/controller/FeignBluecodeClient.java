package com.example.bluecodepay.controller;

import com.example.bluecodepay.model.RequestMessageBluecode;
import com.example.bluecodepay.model.ResponseMessageBluecode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.lxie.feign.springboot.FeignClient;

@FeignClient("bluecode")
@RequestMapping("https://merchant-api.bluecode.biz/v4/")
public interface FeignBluecodeClient {

    @RequestMapping(method = RequestMethod.POST, value = "payment")
    ResponseMessageBluecode startPayment (RequestMessageBluecode requestMessageBluecode);

    @RequestMapping(method = RequestMethod.PATCH, value = "cancel")
    ResponseMessageBluecode capturePayment (RequestMessageBluecode requestMessageBluecode);

    @RequestMapping(method = RequestMethod.POST, value = "status")
    ResponseMessageBluecode statusPayment (RequestMessageBluecode requestMessageBluecode);

    @RequestMapping(method = RequestMethod.POST, value = "refund")
    ResponseMessageBluecode refundPayment (RequestMessageBluecode requestMessageBluecode);

    @RequestMapping(method = RequestMethod.POST, value = "heartbeat")
    ResponseMessageBluecode HeartbeatPayment (RequestMessageBluecode requestMessageBluecode);
}
