package com.example.bluecodepay.client;

import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.response.BluecodeResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
import java.util.UUID;

@Service
@FeignClient(value = "${bluecode.feign.name}", url = "${bluecode.feign.url}")
public interface BluecodeFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "payment")
    BluecodeResponseMessage startPayment(BluecodeRequestMessage startPaymentRequest);

    @RequestMapping(method = RequestMethod.POST, value = "cancel")
    BluecodeResponseMessage cancelPayment(Map<String, UUID> cancelRequest);

    @RequestMapping(method = RequestMethod.POST, value = "status")
    BluecodeResponseMessage statusPayment(Map<String, UUID> statusRequest);

    @RequestMapping(method = RequestMethod.POST, value = "refund")
    BluecodeResponseMessage refundPayment(BluecodeRefundMessage refundRequest);

    @RequestMapping(method = RequestMethod.GET, value = "ping")
    BluecodeResponseMessage pingPayment();
}
