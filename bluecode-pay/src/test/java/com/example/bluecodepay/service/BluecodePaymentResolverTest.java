package com.example.bluecodepay.service;

import com.example.bluecodepay.client.BluecodeFeignClient;
import com.example.bluecodepay.exception.custom.BluecodeFeignBadRequestException;
import com.example.bluecodepay.exception.custom.BluecodeFeignNotFoundException;
import com.example.bluecodepay.exception.custom.BluecodeFeignUnauthorizedException;
import com.example.bluecodepay.exception.custom.BluecodeFeignUnsupportedMethodException;
import com.example.bluecodepay.factory.Resilience4jFactory;
import com.example.bluecodepay.model.enums.Result;
import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.request.BluecodeTransactionMessage;
import com.example.bluecodepay.model.response.BluecodeResponseMessage;
import com.example.bluecodepay.model.response.BluecodeResponseMessageOk;
import com.example.bluecodepay.model.response.BluecodeResponseMessageProcessing;
import com.example.bluecodepay.model.response.Status;
import com.example.bluecodepay.service.resolver.BluecodePaymentResolver;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import template.model.ResponseMessage;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class BluecodePaymentResolverTest {

    private static final String TEST_EXCEPTION_MESSAGE = "test exception message";

    @InjectMocks
    private BluecodePaymentResolver bluecodePaymentResolver;

    @Mock
    private BluecodeFeignClient bluecodeFeignClient;

    @Mock
    private Resilience4jFactory resilience4jFactory;

    @Test
    void shouldBluecodeFeignUnsupportedMethodExceptionUpdatePaymentTest() {
        BluecodeRequestMessage bluecodeRequestMessage = new BluecodeRequestMessage();

        assertThatExceptionOfType(BluecodeFeignUnsupportedMethodException.class)
                .isThrownBy(() -> bluecodePaymentResolver.updatePayment(bluecodeRequestMessage))
                .withMessage("this payment api not support this operation");
    }

    @Test
    void shouldBluecodeFeignUnsupportedMethodExceptionCapturePaymentTest() {
        BluecodeRequestMessage bluecodeRequestMessage = new BluecodeRequestMessage();
        assertThatExceptionOfType(BluecodeFeignUnsupportedMethodException.class)
                .isThrownBy(() -> bluecodePaymentResolver.capturePayment(bluecodeRequestMessage))
                .withMessage("this payment api not support this operation");
    }

    @Test
    void statusPaymentTest() {
        BluecodeTransactionMessage bluecodeTransactionMessage = BluecodeTransactionMessage.builder()
                .withTransactionId(UUID.randomUUID())
                .build();

        when(bluecodeFeignClient.statusPayment(anyMap()))
                .thenReturn(new BluecodeResponseMessage());

        ResponseMessage responseMessage = bluecodePaymentResolver.statusPayment(bluecodeTransactionMessage);

        verify(bluecodeFeignClient, times(1))
                .statusPayment(Collections.singletonMap("merchant_tx_id", bluecodeTransactionMessage.getTransactionId()));

        assertThat(responseMessage).isEqualTo(new BluecodeResponseMessage());
    }

    @Test
    void shouldFeignExceptionStatusPaymentTest() {
        BluecodeTransactionMessage bluecodeTransactionMessage = BluecodeTransactionMessage.builder()
                .withTransactionId(UUID.randomUUID())
                .build();

        when(bluecodeFeignClient.statusPayment(anyMap()))
                .thenThrow(new BluecodeFeignNotFoundException(TEST_EXCEPTION_MESSAGE));

        assertThatExceptionOfType(BluecodeFeignNotFoundException.class)
                .isThrownBy(() -> bluecodePaymentResolver.statusPayment(bluecodeTransactionMessage))
                .withMessage(TEST_EXCEPTION_MESSAGE);

        verify(bluecodeFeignClient, times(1))
                .statusPayment(Collections.singletonMap("merchant_tx_id", bluecodeTransactionMessage.getTransactionId()));
    }


    @Test
    void cancelPaymentTest() {
        BluecodeTransactionMessage bluecodeTransactionMessage = BluecodeTransactionMessage.builder()
                .withTransactionId(UUID.randomUUID())
                .build();

        when(bluecodeFeignClient.cancelPayment(anyMap()))
                .thenReturn(new BluecodeResponseMessage());

        ResponseMessage responseMessage = bluecodePaymentResolver.cancelPayment(bluecodeTransactionMessage);

        verify(bluecodeFeignClient, times(1))
                .cancelPayment(Collections.singletonMap("merchant_tx_id", bluecodeTransactionMessage.getTransactionId()));

        assertThat(responseMessage).isEqualTo(new BluecodeResponseMessage());
    }

    @Test
    void shouldFeignExceptionCancelPaymentTest() {
        BluecodeTransactionMessage bluecodeTransactionMessage = BluecodeTransactionMessage.builder()
                .withTransactionId(UUID.randomUUID())
                .build();

        when(bluecodeFeignClient.cancelPayment(anyMap()))
                .thenThrow(new BluecodeFeignNotFoundException(TEST_EXCEPTION_MESSAGE));

        assertThatExceptionOfType(BluecodeFeignNotFoundException.class)
                .isThrownBy(() -> bluecodePaymentResolver.cancelPayment(bluecodeTransactionMessage))
                .withMessage(TEST_EXCEPTION_MESSAGE);

        verify(bluecodeFeignClient, times(1))
                .cancelPayment(Collections.singletonMap("merchant_tx_id", bluecodeTransactionMessage.getTransactionId()));
    }

    @Test
    void refundPaymentTest() {
        BluecodeRefundMessage bluecodeRefundMessage = new BluecodeRefundMessage();

        when(bluecodeFeignClient.refundPayment(bluecodeRefundMessage))
                .thenReturn(new BluecodeResponseMessage());

        ResponseMessage responseMessage = bluecodePaymentResolver.refundPayment(bluecodeRefundMessage);

        verify(bluecodeFeignClient, times(1))
                .refundPayment(bluecodeRefundMessage);

        assertThat(responseMessage).isEqualTo(new BluecodeResponseMessage());
    }

    @Test
    void shouldFeignExceptionRefundPaymentTest() {
        BluecodeRefundMessage bluecodeRefundMessage = new BluecodeRefundMessage();

        when(bluecodeFeignClient.refundPayment(bluecodeRefundMessage))
                .thenThrow(new BluecodeFeignUnauthorizedException(TEST_EXCEPTION_MESSAGE));

        assertThatExceptionOfType(BluecodeFeignUnauthorizedException.class)
                .isThrownBy(() -> bluecodePaymentResolver.refundPayment(bluecodeRefundMessage))
                .withMessage(TEST_EXCEPTION_MESSAGE);

        verify(bluecodeFeignClient, times(1))
                .refundPayment(bluecodeRefundMessage);
    }

    @Test
    void startPaymentTest() {
        BluecodeRequestMessage bluecodeRequestMessage = BluecodeRequestMessage.builder()
                .withId(UUID.randomUUID())
                .build();

        BluecodeResponseMessage bluecodeResponseMessage = BluecodeResponseMessage.builder()
                .withResult(Result.OK)
                .build();

        when(bluecodeFeignClient.startPayment(bluecodeRequestMessage))
                .thenReturn(bluecodeResponseMessage);

        ResponseMessage responseMessage = bluecodePaymentResolver.startPayment(bluecodeRequestMessage);

        verify(bluecodeFeignClient, times(1))
                .startPayment(bluecodeRequestMessage);

        assertThat(responseMessage).isEqualTo(bluecodeResponseMessage);
    }

    @Test
    void shouldBluecodeFeignBadRequestStartPaymentTest() {
        BluecodeRequestMessage bluecodeRequestMessage = BluecodeRequestMessage.builder()
                .withId(UUID.randomUUID())
                .build();

        when(bluecodeFeignClient.startPayment(bluecodeRequestMessage))
                .thenThrow(new BluecodeFeignBadRequestException(TEST_EXCEPTION_MESSAGE));

        assertThatExceptionOfType(BluecodeFeignBadRequestException.class)
                .isThrownBy(() -> bluecodePaymentResolver.startPayment(bluecodeRequestMessage))
                .withMessage(TEST_EXCEPTION_MESSAGE);

        verify(bluecodeFeignClient, times(1))
                .startPayment(bluecodeRequestMessage);

    }

    @Test
    void whenResultProcessingStartPaymentTest() {
        BluecodeRequestMessage bluecodeRequestMessage = BluecodeRequestMessage.builder()
                .withId(UUID.randomUUID())
                .build();

        Status status = Status.builder()
                .withCheckStatusIn(1000)
                .withTtl(30000)
                .withMerchantTxId(bluecodeRequestMessage.getId().toString())
                .build();

        Retry retry = RetryRegistry.ofDefaults().retry("test");

        BluecodeResponseMessage bluecodeResponseMessage = BluecodeResponseMessageProcessing.builder()
                .withStatus(status)
                .build();

        when(bluecodeFeignClient.startPayment(bluecodeRequestMessage))
                .thenReturn(bluecodeResponseMessage);
        when(resilience4jFactory.getConfigureRetry(status))
                .thenReturn(retry);
        when(bluecodeFeignClient.statusPayment(anyMap()))
                .thenReturn(new BluecodeResponseMessageOk());

        ResponseMessage responseMessage = bluecodePaymentResolver.startPayment(bluecodeRequestMessage);

        assertThat(responseMessage).isEqualTo(new BluecodeResponseMessageOk());
    }
}
