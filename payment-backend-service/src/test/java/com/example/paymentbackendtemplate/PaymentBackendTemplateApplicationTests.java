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
    void contextLoads() throws JsonProcessingException {
        final RequestMessage requestMessage = new RequestMessageBluecode(
                "description_test",
                "test_slip",
                "my_terminal",
                "ecommerce",
                "scan",
                "4334g4g",
                "9903049950293",
                10,
                150
        );
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestMessage));
        System.out.println(requestFacade.startTransaction(requestMessage));
    }

    @Test
    void getObjMapperWithSubTypes () throws JsonProcessingException {
        final RequestMessageBluecode requestMess = new RequestMessageBluecode(
                "description_test",
                "test_slip",
                "my_terminal",
                "ecommerce",
                "scan",
                "4334g4g",
                "9903049950293",
                10,
                122
        );
        final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestMess);
        System.out.println(json);
        RequestMessage requestMessage = objectMapper.readValue(json, RequestMessage.class);
        paymentRepo.save(requestMessage);
        paymentRepo.findAll().forEach(System.out::println);
        System.out.println(requestFacade.startTransaction(requestMessage));
    }


}
