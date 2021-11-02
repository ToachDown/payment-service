package com.example.paymentbackendtemplate;

import com.example.bluecodepay.model.RequestMessageBluecode;
import com.example.paymentbackendtemplate.service.RequestFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import template.model.RequestMessage;

@SpringBootTest
class PaymentBackendTemplateApplicationTests {

    @Autowired
    private RequestFacade requestFacade;


    @Test
    void contextLoads() {
        final RequestMessageBluecode requestMessage = new RequestMessageBluecode("bluecode-pay");
        System.out.println(requestFacade.getAnswer(requestMessage));
    }

}
