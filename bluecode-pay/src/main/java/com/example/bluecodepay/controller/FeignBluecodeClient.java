package com.example.bluecodepay.controller;

import com.example.bluecodepay.model.request.RefundBluecodeRequest;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "FeignBluecode", url = "https://merchant-api.bluecode.biz/v4/")
public interface FeignBluecodeClient {

    @RequestMapping(method = RequestMethod.POST, value = "payment")
    ResponseMessageBluecode startPayment(RequestMessageBluecode requestMessageBluecode);

    @RequestMapping(method = RequestMethod.POST, value = "cancel")
    ResponseMessageBluecode capturePayment(RequestMessageBluecode merchant_tx_id);

    @RequestMapping(method = RequestMethod.POST, value = "status")
    ResponseMessageBluecode statusPayment(String merchant_tx_id);

    @RequestMapping(method = RequestMethod.POST, value = "refund")
    ResponseMessageBluecode refundPayment(RefundBluecodeRequest refundBluecodeRequest);

    @RequestMapping(method = RequestMethod.GET, value = "ping")
    ResponseMessageBluecode heartbeatPayment();
}
