package com.example.paymentbackendtemplate.integration;

import com.example.backendtemplate.model.RefundMessage;
import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.ResponseMessage;
import com.example.backendtemplate.model.TransactionMessage;
import com.example.backendtemplate.model.dto.PaymentDto;
import com.example.backendtemplate.model.dto.RefundPaymentDto;
import com.example.backendtemplate.model.dto.TransactionDto;
import com.example.bluecodepay.exception.custom.BluecodeFeignUnsupportedMethodException;
import com.example.paymentbackendtemplate.controller.PaymentController;
import com.example.paymentbackendtemplate.exception.custom.DataBaseNotFoundException;
import com.example.paymentbackendtemplate.helpers.JsonTestHelper;
import com.example.paymentbackendtemplate.implementation.RefundMessageTest;
import com.example.paymentbackendtemplate.implementation.RequestMessageTest;
import com.example.paymentbackendtemplate.implementation.ResponseMessageTest;
import com.example.paymentbackendtemplate.implementation.TransactionMessageTest;
import com.example.paymentbackendtemplate.repository.PaymentRepository;
import com.example.paymentbackendtemplate.service.RequestCommander;
import com.example.paymentbackendtemplate.service.TransformCommander;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class IntegrationControllerTest {

    @Autowired
    private PaymentController paymentController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private TransformCommander transformCommander;

    @MockBean
    private RequestCommander requestCommander;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("paymentController"));
    }

    @Test
    public void startPaymentTest() throws Exception {
        RequestMessage requestMessage = RequestMessageTest.builder()
                .withId(UUID.randomUUID())
                .withType("bluecode")
                .build();
        ResponseMessage responseMessage = ResponseMessageTest.builder()
                .withType("bluecode")
                .build();
        when(transformCommander.transformPaymentDto(any(PaymentDto.class)))
                .thenReturn(requestMessage);
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(requestMessage);
        when(requestCommander.startTransaction(requestMessage))
                .thenReturn(responseMessage);

        this.mockMvc.perform(post("/api/start-payment")
                        .content(JsonTestHelper.getStartRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("bluecode"));
    }

    @Test
    public void shouldBadRequestExceptionStartPaymentTest() throws Exception {
        this.mockMvc.perform(post("/api/start-payment")
                        .content(JsonTestHelper.getStartBrokenRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePaymentTest() throws Exception {
        RequestMessage requestMessage = RequestMessageTest.builder()
                .withId(UUID.randomUUID())
                .withType("bluecode")
                .build();
        ResponseMessage responseMessage = ResponseMessageTest.builder()
                .withType("bluecode")
                .build();
        when(transformCommander.transformPaymentDto(any(PaymentDto.class)))
                .thenReturn(requestMessage);
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(requestMessage);
        when(requestCommander.updatePayment(requestMessage))
                .thenReturn(responseMessage);

        this.mockMvc.perform(patch("/api/update-payment")
                        .content(JsonTestHelper.getUpdateRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("bluecode"));
    }

    @Test
    public void shouldBadRequestExceptionUpdatePaymentTest() throws Exception {
        this.mockMvc.perform(patch("/api/update-payment")
                        .content(JsonTestHelper.getUpdateBrokenRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldMethodNotAllowedUpdatePaymentTest() throws Exception {
        RequestMessage requestMessage = RequestMessageTest.builder()
                .withId(UUID.randomUUID())
                .withType("bluecode")
                .build();
        when(transformCommander.transformPaymentDto(any(PaymentDto.class)))
                .thenReturn(requestMessage);
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(requestMessage);
        when(requestCommander.updatePayment(requestMessage))
                .thenThrow(new BluecodeFeignUnsupportedMethodException());

        this.mockMvc.perform(patch("/api/update-payment")
                        .content(JsonTestHelper.getUpdateRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void capturePaymentTest() throws Exception {
        RequestMessage requestMessage = RequestMessageTest.builder()
                .withId(UUID.randomUUID())
                .withType("bluecode")
                .build();
        ResponseMessage responseMessage = ResponseMessageTest.builder()
                .withType("bluecode")
                .build();
        when(transformCommander.transformPaymentDto(any(PaymentDto.class)))
                .thenReturn(requestMessage);
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(requestMessage);
        when(requestCommander.captureTransaction(requestMessage))
                .thenReturn(responseMessage);

        this.mockMvc.perform(post("/api/capture-payment")
                        .content(JsonTestHelper.getCaptureRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("bluecode"));
    }

    @Test
    public void shouldBadRequestExceptionCapturePaymentTest() throws Exception {
        this.mockMvc.perform(post("/api/capture-payment")
                        .content(JsonTestHelper.getCaptureBrokenRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldMethodNotAllowedCapturePaymentTest() throws Exception {
        RequestMessage requestMessage = RequestMessageTest.builder()
                .withId(UUID.randomUUID())
                .withType("bluecode")
                .build();
        when(transformCommander.transformPaymentDto(any(PaymentDto.class)))
                .thenReturn(requestMessage);
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(requestMessage);
        when(requestCommander.captureTransaction(requestMessage))
                .thenThrow(new BluecodeFeignUnsupportedMethodException());

        this.mockMvc.perform(post("/api/capture-payment")
                        .content(JsonTestHelper.getCaptureRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void cancelPaymentTest() throws Exception {
        TransactionMessage txMessage = TransactionMessageTest.builder()
                .withType("bluecode")
                .build();
        RequestMessage requestMessage = RequestMessageTest.builder()
                .withType("bluecode")
                .build();
        ResponseMessage responseMessage = ResponseMessageTest.builder()
                .withType("bluecode")
                .build();
        when(transformCommander.transformPaymentIdDto(any(TransactionDto.class)))
                .thenReturn(txMessage);
        when(paymentRepository.getById(any()))
                .thenReturn(requestMessage);
        when(requestCommander.cancelTransaction(txMessage))
                .thenReturn(responseMessage);

        this.mockMvc.perform(post("/api/cancel")
                        .content(JsonTestHelper.getCancelRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("bluecode"));
    }

    @Test
    public void shouldBadRequestExceptionCancelPaymentTest() throws Exception {
        this.mockMvc.perform(post("/api/cancel")
                        .content(JsonTestHelper.getCancelBrokenRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldDataBaseExceptionCancelPaymentTest() throws Exception {
        TransactionMessage txMessage = TransactionMessageTest.builder()
                .withType("bluecode")
                .build();
        when(transformCommander.transformPaymentIdDto(any(TransactionDto.class)))
                .thenReturn(txMessage);
        when(paymentRepository.getById(any()))
                .thenThrow(new DataBaseNotFoundException("payment with id [" + "61a2dd60-4f62-11ec-81d3-0242ac130003" + "]"));

        this.mockMvc.perform(post("/api/cancel")
                        .content(JsonTestHelper.getCancelRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void refundPaymentTest() throws Exception {
        RefundMessage refundMessage = RefundMessageTest.builder()
                .withType("bluecode")
                .build();
        RequestMessage requestMessage = RequestMessageTest.builder()
                .withType("bluecode")
                .build();
        ResponseMessage responseMessage = ResponseMessageTest.builder()
                .withType("bluecode")
                .build();
        when(transformCommander.transformRefundDto(any(RefundPaymentDto.class)))
                .thenReturn(refundMessage);
        when(paymentRepository.getById(any()))
                .thenReturn(requestMessage);
        when(requestCommander.refundPayment(refundMessage))
                .thenReturn(responseMessage);

        this.mockMvc.perform(post("/api/refund")
                        .content(JsonTestHelper.getRefundRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("bluecode"));
    }

    @Test
    public void shouldBadRequestExceptionRefundPaymentTest() throws Exception {
        this.mockMvc.perform(post("/api/refund")
                        .content(JsonTestHelper.getRefundBrokenRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldDataBaseExceptionRefundPaymentTest() throws Exception {

        when(paymentRepository.getById(any()))
                .thenThrow(new DataBaseNotFoundException("payment with id [" + "61a2dd60-4f62-11ec-81d3-0242ac130003" + "]"));

        this.mockMvc.perform(post("/api/refund")
                        .content(JsonTestHelper.getRefundRequestJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void statusPaymentTest() throws Exception {
        TransactionMessage transactionMessage = TransactionMessageTest.builder()
                .withType("bluecode")
                .build();
        ResponseMessage responseMessage = ResponseMessageTest.builder()
                .withType("bluecode")
                .build();
        when(transformCommander.transformPaymentIdDto(any(TransactionDto.class)))
                .thenReturn(transactionMessage);
        when(requestCommander.statusTransaction(any(TransactionMessage.class)))
                .thenReturn(responseMessage);

        this.mockMvc.perform(get("/api/{api}/get-payment/{paymentId}", "bluecode", "61a2dd60-4f62-11ec-81d3-0242ac130003"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type").value("bluecode"));
    }

    @Test
    public void shouldBadRequestExceptionStatusPaymentTest() throws Exception {
        this.mockMvc.perform(get("/api/{api}/get-payment/{paymentId}", "bluecod", "61a2dd60-4f62-1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}
