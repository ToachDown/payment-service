package com.example.bluecodepay.service;

import com.example.bluecodepay.exception.custom.BluecodeFeignBadRequestException;
import com.example.bluecodepay.exception.custom.BluecodeFeignNotFoundException;
import com.example.bluecodepay.exception.custom.BluecodeTransformBadParametersException;
import com.example.bluecodepay.model.enums.Barcode;
import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.example.bluecodepay.model.enums.State;
import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.request.BluecodeTransactionMessage;
import com.example.bluecodepay.transformer.BluecodeTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.TransactionMessage;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith({MockitoExtension.class})
public class TransformServiceTest {

    @InjectMocks
    private BluecodeTransformer bluecodeTransformer;

    @BeforeEach
    public void setUp() {
        bluecodeTransformer.setTip(4);
    }

    @Test
    void calculatedTimAmountTest() {
       int tip = 5;
       int amount = 1000;
       int discount = 100;
       int tipAmount = bluecodeTransformer.calculatedTipAmount(tip, amount, discount);
       assertThat(45).isEqualTo(tipAmount);
    }

    @Test
    void whenTipNullCalculatedTimAmountTest() {
        int tip = 0;
        int amount = 1000;
        int discount = 100;
        int tipAmount = bluecodeTransformer.calculatedTipAmount(tip, amount, discount);
        assertThat(0).isEqualTo(tipAmount);
    }

    @Test
    void whenAmountNullCalculatedTimAmountTest() {
        int tip = 5;
        int amount = 0;
        int discount = 0;
        int tipAmount = bluecodeTransformer.calculatedTipAmount(tip, amount, discount);
        assertThat(0).isEqualTo(tipAmount);
    }

    @Test
    void calculatedClearAmountTest(){
        int tip = 4;
        int amount = 1000;
        int discount = 100;
        int clearAmount = bluecodeTransformer.calculatingClearAmount(amount, tip, discount);
        assertThat(936).isEqualTo(clearAmount);
    }

    @Test
    void whenTipNullCalculatedClearAmountTest() {
        int tip = 0;
        int amount = 1000;
        int discount = 100;
        int clearAmount = bluecodeTransformer.calculatingClearAmount(amount, tip, discount);
        assertThat(900).isEqualTo(clearAmount);
    }

    @Test
    void whenDiscountNullCalculatedClearAmountTest() {
        int tip = 5;
        int amount = 1000;
        int discount = 0;
        int clearAmount = bluecodeTransformer.calculatingClearAmount(amount, tip, discount);
        assertThat(1050).isEqualTo(clearAmount);
    }

    @Test
    void whenDiscountMoreThanAmountCalculatedClearAmountTest() {
        int tip = 5;
        int amount = 150;
        int discount = 200;
        assertThatExceptionOfType(BluecodeTransformBadParametersException.class)
                .isThrownBy(() -> bluecodeTransformer.calculatingClearAmount(amount, tip, discount))
                .withMessage("Bad request: discount > amount");
    }

    @Test
    void transformRequestMessageTest() {
        PaymentDto paymentDto = PaymentDto.builder()
                .withCurrency("EUR")
                .withAmount(1000)
                .withBarcode("SUCCESS_ALWAYS")
                .withInstantPayment(false)
                .withDiscountAmount(100)
                .withSource("pos")
                .withDescription("test desc")
                .build();

        RequestMessage requestMessagePattern = BluecodeRequestMessage.builder()
                .withCurrency(Currency.valueOf(paymentDto.getCurrency()))
                .withType(paymentDto.getApi())
                .withState(State.TODO)
                .withScheme(Scheme.AUTO)
                .withPurchaseAmount(paymentDto.getAmount())
                .withDiscountAmount(paymentDto.getDiscountAmount())
                .withTipAmount(36)
                .withRequestedAmount(936)
                .withBarcode(Barcode.valueOf(paymentDto.getBarcode()).getBarcode())
                .withBranchExtId("test")
                .withEndToEndId(null)
                .withSlipNote(paymentDto.getDescription())
                .build();

        RequestMessage requestMessage = bluecodeTransformer.transformRequestMessage(paymentDto);

        assertThat(requestMessage).isEqualTo(requestMessagePattern);
    }

    @Test
    void transformRefundMessageTest() {
        RefundPaymentDto refundPaymentDto = RefundPaymentDto.builder()
                .withApi("bluecode")
                .withAcquirerId("test_acq")
                .withTransactionId(UUID.randomUUID())
                .withAmount(1000)
                .withReason("test reason")
                .build();

        RefundMessage refundMessagePattern = BluecodeRefundMessage.builder()
                .withType(refundPaymentDto.getApi())
                .withAmount(refundPaymentDto.getAmount())
                .withReason(refundPaymentDto.getReason())
                .withAcquirerTransactionId(refundPaymentDto.getAcquirerId())
                .build();

        RefundMessage refundMessage = bluecodeTransformer.transformRefundMessage(refundPaymentDto);

        assertThat(refundMessage).isEqualTo(refundMessagePattern);
    }

    @Test
    void transformTransactionMessageTest() {
        TransactionDto transactionDto = TransactionDto.builder()
                .withApi("bluecode")
                .withPaymentId(UUID.randomUUID())
                .build();

        TransactionMessage transactionMessagePattern = BluecodeTransactionMessage.builder()
                .withTransactionId(transactionDto.getPaymentId())
                .withType(transactionDto.getApi())
                .build();

        TransactionMessage transactionMessage = bluecodeTransformer.transformTransactionMessage(transactionDto);

        assertThat(transactionMessage).isEqualTo(transactionMessagePattern);
    }


}
