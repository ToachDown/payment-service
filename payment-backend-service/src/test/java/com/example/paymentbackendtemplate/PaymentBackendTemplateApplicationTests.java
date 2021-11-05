package com.example.paymentbackendtemplate;

import com.example.bluecodepay.model.RequestMessageBluecode;
import com.example.paymentbackendtemplate.repository.PaymentRepository;
import com.example.paymentbackendtemplate.service.RequestFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import template.model.RequestMessage;

import java.util.List;

@SpringBootTest
class PaymentBackendTemplateApplicationTests {

    @Autowired
    private RequestFacade requestFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentRepository paymentRepo;


    @Test
    void contextLoads() {
        final RequestMessage requestMessage = new RequestMessageBluecode("test", "test_bar", 12);
        System.out.println(requestFacade.getAnswer(requestMessage));
    }

    @Test
    void getObjMapperWithSubTypes () throws JsonProcessingException {
        final RequestMessageBluecode requestMess = new RequestMessageBluecode("test_t", "test_bar", 12);
        final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestMess);
        System.out.println(json);
        RequestMessage requestMessage = objectMapper.readValue(json, RequestMessage.class);
        paymentRepo.save(requestMessage);
        paymentRepo.findAll().forEach(System.out::println);
        System.out.println(requestFacade.getAnswer(requestMessage));
    }


}
