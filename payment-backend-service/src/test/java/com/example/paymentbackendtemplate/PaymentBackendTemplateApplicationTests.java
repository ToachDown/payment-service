package com.example.paymentbackendtemplate;

import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import com.example.paymentbackendtemplate.repository.PaymentRepository;
import com.example.paymentbackendtemplate.service.RequestCommander;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import template.model.RequestMessage;
import template.model.ResponseMessage;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PaymentBackendTemplateApplicationTests {

    @Autowired
    private RequestCommander requestCommander;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentRepository paymentRepo;


    @Test
    void contextLoads() throws JsonProcessingException {
        final RequestMessage requestMessage = new RequestMessageBluecode();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestMessage));
        System.out.println(requestCommander.startTransaction(requestMessage));
    }

    @Test
    void getObjMapperWithSubTypes () throws JsonProcessingException {
        final RequestMessageBluecode requestMess = new RequestMessageBluecode();
        final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestMess);
        System.out.println(json);
        RequestMessage requestMessage = objectMapper.readValue(json, RequestMessage.class);
        paymentRepo.save(requestMessage);
        paymentRepo.findAll().forEach(System.out::println);
        System.out.println(requestCommander.startTransaction(requestMessage));
    }

    @Test
    void ResponseTest () throws JsonProcessingException {
 //       ResponseBluecodeProcessing statusDetail = new ResponseBluecodeProcessing();
 //       ResponseMessage responseMessage = new ResponseMessageBluecode();
//        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMessage);
//        System.out.println(json);
//        System.out.println(objectMapper.readValue(json, ResponseMessage.class));
    }


}
