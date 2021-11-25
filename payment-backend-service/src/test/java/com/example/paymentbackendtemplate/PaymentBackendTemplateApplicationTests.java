package com.example.paymentbackendtemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import template.exception.ApiFeignException;

@ExtendWith(MockitoExtension.class)
class PaymentBackendTemplateApplicationTests {


    @Test
    void contextLoads() throws JsonProcessingException, ApiFeignException {

    }

    @Test
    void getObjMapperWithSubTypes() throws JsonProcessingException, ApiFeignException {

    }

    @Test
    void ResponseTest() throws JsonProcessingException {
        //       ResponseBluecodeProcessing statusDetail = new ResponseBluecodeProcessing();
        //       ResponseMessage responseMessage = new ResponseMessageBluecode();
//        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMessage);
//        System.out.println(json);
//        System.out.println(objectMapper.readValue(json, ResponseMessage.class));
    }

    @Test
    void testsResilience4j() {

    }


}
