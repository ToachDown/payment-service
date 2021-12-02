package com.example.paymentbackendtemplate.junit;

import com.example.backendtemplate.exception.ApiFeignException;
import com.example.backendtemplate.exception.ApiTransformException;
import com.example.backendtemplate.model.RefundMessage;
import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.ResponseMessage;
import com.example.backendtemplate.model.TransactionMessage;
import com.example.backendtemplate.model.dto.PaymentDto;
import com.example.backendtemplate.model.dto.RefundPaymentDto;
import com.example.backendtemplate.model.dto.TransactionDto;
import com.example.bluecodepay.exception.custom.BluecodeFeignInternalException;
import com.example.bluecodepay.exception.custom.BluecodeTransformBadParametersException;
import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.request.BluecodeTransactionMessage;
import com.example.bluecodepay.model.response.BluecodeResponseMessage;
import com.example.paymentbackendtemplate.exception.custom.DataBaseNotFoundException;
import com.example.paymentbackendtemplate.implementation.RefundMessageTest;
import com.example.paymentbackendtemplate.implementation.RequestMessageTest;
import com.example.paymentbackendtemplate.implementation.ResponseMessageTest;
import com.example.paymentbackendtemplate.implementation.TransactionMessageTest;
import com.example.paymentbackendtemplate.repository.PaymentRepository;
import com.example.paymentbackendtemplate.service.PaymentService;
import com.example.paymentbackendtemplate.service.RequestCommander;
import com.example.paymentbackendtemplate.service.TransformCommander;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith({MockitoExtension.class})
public class PaymentServiceTest {

    private static final String EXCEPTION_TEST_MESSAGE = "test error request";

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private TransformCommander transformCommander;

    @Mock
    private RequestCommander requestCommander;

    @BeforeEach
    void setUp() {

    }

    @Test
    void beginTransactionTest() {
        //given
        PaymentDto paymentDto = new PaymentDto();
        //when
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(new BluecodeRequestMessage());
        when(transformCommander.transformPaymentDto(paymentDto))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.startTransaction(any(RequestMessage.class)))
                .thenReturn(new BluecodeResponseMessage());
        when(requestCommander.changePaymentStateWithResponse(any(RequestMessage.class), any(ResponseMessage.class)))
                .thenReturn(new BluecodeRequestMessage());

        ResponseMessage responseMessage = paymentService.beginTransaction(paymentDto);
        //then
        verify(transformCommander, times(1))
                .transformPaymentDto(paymentDto);
        verify(paymentRepository, times(1))
                .saveAndFlush(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .startTransaction(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .changePaymentStateWithResponse(any(BluecodeRequestMessage.class), any(BluecodeResponseMessage.class));
        verify(paymentRepository, times(1))
                .save(any(BluecodeRequestMessage.class));
        assertNotNull(responseMessage);
        assertThat(new BluecodeResponseMessage()).isEqualTo(responseMessage);
    }

    @Test
    void whenTestImplementationBeginTransactionTest() {
        //given
        PaymentDto paymentDto = new PaymentDto();
        //when
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(new RequestMessageTest());
        when(transformCommander.transformPaymentDto(paymentDto))
                .thenReturn(new RequestMessageTest());
        when(requestCommander.startTransaction(any(RequestMessage.class)))
                .thenReturn(new ResponseMessageTest());
        when(requestCommander.changePaymentStateWithResponse(any(RequestMessage.class), any(ResponseMessage.class)))
                .thenReturn(new RequestMessageTest());

        ResponseMessage responseMessage = paymentService.beginTransaction(paymentDto);
        //then
        verify(transformCommander, times(1))
                .transformPaymentDto(paymentDto);
        verify(paymentRepository, times(1))
                .saveAndFlush(any(RequestMessageTest.class));
        verify(requestCommander, times(1))
                .startTransaction(any(RequestMessageTest.class));
        verify(requestCommander, times(1))
                .changePaymentStateWithResponse(any(RequestMessageTest.class), any(ResponseMessageTest.class));
        verify(paymentRepository, times(1))
                .save(any(RequestMessageTest.class));
        assertNotNull(responseMessage);
        assertThat(new ResponseMessageTest()).isEqualTo(responseMessage);
    }

    @Test
    void shouldTriggerApiExceptionStartPaymentTest() {
        PaymentDto paymentDto = new PaymentDto();
        //when
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(new BluecodeRequestMessage());
        when(transformCommander.transformPaymentDto(paymentDto))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.startTransaction(any(RequestMessage.class)))
                .thenThrow(new BluecodeFeignInternalException(EXCEPTION_TEST_MESSAGE));

        assertThatExceptionOfType(ApiFeignException.class)
                .isThrownBy(() -> paymentService.beginTransaction(paymentDto))
                .withMessage(EXCEPTION_TEST_MESSAGE);


        verify(transformCommander, times(1))
                .transformPaymentDto(paymentDto);
        verify(paymentRepository, times(1))
                .saveAndFlush(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .startTransaction(any(BluecodeRequestMessage.class));

    }

    @Test
    void shouldTriggerTransformExceptionStartPaymentTest() {
        PaymentDto paymentDto = new PaymentDto();
        //when

        when(transformCommander.transformPaymentDto(eq(paymentDto)))
                .thenThrow(new BluecodeTransformBadParametersException(EXCEPTION_TEST_MESSAGE));

        assertThatExceptionOfType(ApiTransformException.class)
                .isThrownBy(() -> paymentService.beginTransaction(paymentDto))
                .withMessage(EXCEPTION_TEST_MESSAGE);


        verify(transformCommander, times(1))
                .transformPaymentDto(eq(paymentDto));

    }

    @Test
    void updatePaymentTest() {
        //given
        PaymentDto paymentDto = new PaymentDto();
        //when
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(new BluecodeRequestMessage());
        when(transformCommander.transformPaymentDto(paymentDto))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.updatePayment(any(RequestMessage.class)))
                .thenReturn(new BluecodeResponseMessage());
        when(requestCommander.changePaymentStateWithResponse(any(RequestMessage.class), any(ResponseMessage.class)))
                .thenReturn(new BluecodeRequestMessage());

        ResponseMessage responseMessage = paymentService.updatePayment(paymentDto);
        //then
        verify(transformCommander, times(1))
                .transformPaymentDto(paymentDto);
        verify(paymentRepository, times(1))
                .saveAndFlush(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .updatePayment(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .changePaymentStateWithResponse(any(BluecodeRequestMessage.class), any(BluecodeResponseMessage.class));
        verify(paymentRepository, times(1))
                .save(any(BluecodeRequestMessage.class));

        assertNotNull(responseMessage);
        assertThat(new BluecodeResponseMessage()).isEqualTo(responseMessage);
    }

    @Test
    void whenTestImplementationUpdatePaymentTest() {
        //given
        PaymentDto paymentDto = new PaymentDto();
        //when
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(new RequestMessageTest());
        when(transformCommander.transformPaymentDto(paymentDto))
                .thenReturn(new RequestMessageTest());
        when(requestCommander.updatePayment(any(RequestMessage.class)))
                .thenReturn(new ResponseMessageTest());
        when(requestCommander.changePaymentStateWithResponse(any(RequestMessage.class), any(ResponseMessage.class)))
                .thenReturn(new RequestMessageTest());

        ResponseMessage responseMessage = paymentService.updatePayment(paymentDto);
        //then
        verify(transformCommander, times(1))
                .transformPaymentDto(paymentDto);
        verify(paymentRepository, times(1))
                .saveAndFlush(any(RequestMessageTest.class));
        verify(requestCommander, times(1))
                .updatePayment(any(RequestMessageTest.class));
        verify(requestCommander, times(1))
                .changePaymentStateWithResponse(any(RequestMessageTest.class), any(ResponseMessageTest.class));
        verify(paymentRepository, times(1))
                .save(any(RequestMessageTest.class));
        assertNotNull(responseMessage);
        assertThat(new ResponseMessageTest()).isEqualTo(responseMessage);
    }

    @Test
    void shouldTriggerApiExceptionUpdatePaymentTest() {
        PaymentDto paymentDto = new PaymentDto();
        //when
//        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
//                .thenReturn(new BluecodeRequestMessage());
//        when(transformCommander.transformPaymentDto(paymentDto))
//                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.updatePayment(any()))
                .thenThrow(new BluecodeFeignInternalException(EXCEPTION_TEST_MESSAGE));

        assertThatExceptionOfType(ApiFeignException.class)
                .isThrownBy(() -> paymentService.updatePayment(paymentDto))
                .withMessage(EXCEPTION_TEST_MESSAGE);

//        verify(transformCommander, times(1))
//                .transformPaymentDto(paymentDto);
//        verify(paymentRepository, times(1))
//                .saveAndFlush(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .updatePayment(any());
    }

    @Test
    void capturePaymentTest() {
        //given
        PaymentDto paymentDto = new PaymentDto();
        //when
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(new BluecodeRequestMessage());
        when(transformCommander.transformPaymentDto(paymentDto))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.captureTransaction(any(RequestMessage.class)))
                .thenReturn(new BluecodeResponseMessage());
        when(requestCommander.changePaymentStateWithResponse(any(RequestMessage.class), any(ResponseMessage.class)))
                .thenReturn(new BluecodeRequestMessage());

        ResponseMessage responseMessage = paymentService.capturePayment(paymentDto);
        //then
        verify(transformCommander, times(1))
                .transformPaymentDto(paymentDto);
        verify(paymentRepository, times(1))
                .saveAndFlush(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .captureTransaction(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .changePaymentStateWithResponse(any(BluecodeRequestMessage.class), any(BluecodeResponseMessage.class));
        verify(paymentRepository, times(1))
                .save(any(BluecodeRequestMessage.class));

        assertNotNull(responseMessage);
        assertThat(new BluecodeResponseMessage()).isEqualTo(responseMessage);
    }

    @Test
    void whenTestImplementationCapturePaymentTest() {
        //given
        PaymentDto paymentDto = new PaymentDto();
        //when
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(new RequestMessageTest());
        when(transformCommander.transformPaymentDto(paymentDto))
                .thenReturn(new RequestMessageTest());
        when(requestCommander.captureTransaction(any(RequestMessage.class)))
                .thenReturn(new ResponseMessageTest());
        when(requestCommander.changePaymentStateWithResponse(any(RequestMessage.class), any(ResponseMessage.class)))
                .thenReturn(new RequestMessageTest());

        ResponseMessage responseMessage = paymentService.capturePayment(paymentDto);
        //then
        verify(transformCommander, times(1))
                .transformPaymentDto(paymentDto);
        verify(paymentRepository, times(1))
                .saveAndFlush(any(RequestMessageTest.class));
        verify(requestCommander, times(1))
                .captureTransaction(any(RequestMessageTest.class));
        verify(requestCommander, times(1))
                .changePaymentStateWithResponse(any(RequestMessageTest.class), any(ResponseMessageTest.class));
        verify(paymentRepository, times(1))
                .save(any(RequestMessageTest.class));

        assertNotNull(responseMessage);
        assertThat(new ResponseMessageTest()).isEqualTo(responseMessage);
    }

    @Test
    void shouldTriggerApiExceptionCapturePaymentTest() {
        PaymentDto paymentDto = new PaymentDto();
        //when
        when(paymentRepository.saveAndFlush(any(RequestMessage.class)))
                .thenReturn(new BluecodeRequestMessage());
        when(transformCommander.transformPaymentDto(paymentDto))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.captureTransaction(any(RequestMessage.class)))
                .thenThrow(new BluecodeFeignInternalException(EXCEPTION_TEST_MESSAGE));

        assertThatExceptionOfType(ApiFeignException.class)
                .isThrownBy(() -> paymentService.capturePayment(paymentDto))
                .withMessage(EXCEPTION_TEST_MESSAGE);

        verify(transformCommander, times(1))
                .transformPaymentDto(eq(paymentDto));
        verify(paymentRepository, times(1))
                .saveAndFlush(any(BluecodeRequestMessage.class));
        verify(requestCommander, times(1))
                .captureTransaction(any(BluecodeRequestMessage.class));
    }

    @Test
    void refundPaymentTest() {
        RefundPaymentDto refundPaymentDto = RefundPaymentDto.builder()
                .withApi("bluecode")
                .withAcquirerId("test_ac_id")
                .withTransactionId(UUID.randomUUID())
                .build();
        when(transformCommander.transformRefundDto(refundPaymentDto))
                .thenReturn(new BluecodeRefundMessage());
        when(paymentRepository.getById(eq(refundPaymentDto.getTransactionId())))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.refundPayment(any(RefundMessage.class)))
                .thenReturn(new BluecodeResponseMessage());
        when(requestCommander.changePaymentState(any(RequestMessage.class), eq("REFUNDED")))
                .thenReturn(new BluecodeRequestMessage());

        ResponseMessage responseMessage = paymentService.refundPayment(refundPaymentDto);

        verify(transformCommander, times(1))
                .transformRefundDto(refundPaymentDto);
        verify(paymentRepository, times(1))
                .getById(eq(refundPaymentDto.getTransactionId()));
        verify(requestCommander, times(1))
                .refundPayment(any(BluecodeRefundMessage.class));
        verify(requestCommander, times(1))
                .changePaymentState(any(BluecodeRequestMessage.class), eq("REFUNDED"));

        assertNotNull(responseMessage);
        assertThat(new BluecodeResponseMessage()).isEqualTo(responseMessage);
    }

    @Test
    void whenTestImplementationRefundPaymentTest() {
        RefundPaymentDto refundPaymentDto = RefundPaymentDto.builder()
                .withApi("bluecode")
                .withAcquirerId("test_ac_id")
                .withTransactionId(UUID.randomUUID())
                .build();
        when(transformCommander.transformRefundDto(refundPaymentDto))
                .thenReturn(new RefundMessageTest());
        when(paymentRepository.getById(eq(refundPaymentDto.getTransactionId())))
                .thenReturn(new RequestMessageTest());
        when(requestCommander.refundPayment(any(RefundMessage.class)))
                .thenReturn(new ResponseMessageTest());
        when(requestCommander.changePaymentState(any(RequestMessage.class), eq("REFUNDED")))
                .thenReturn(new RequestMessageTest());

        ResponseMessage responseMessage = paymentService.refundPayment(refundPaymentDto);

        verify(transformCommander, times(1))
                .transformRefundDto(refundPaymentDto);
        verify(paymentRepository, times(1))
                .getById(eq(refundPaymentDto.getTransactionId()));
        verify(requestCommander, times(1))
                .refundPayment(any(RefundMessageTest.class));
        verify(requestCommander, times(1))
                .changePaymentState(any(RequestMessageTest.class), eq("REFUNDED"));

        assertNotNull(responseMessage);
        assertThat(new ResponseMessageTest()).isEqualTo(responseMessage);
    }

    @Test
    void shouldTriggerApiExceptionRefundPaymentTest() {
        RefundPaymentDto refundPaymentDto = RefundPaymentDto.builder()
                .withApi("bluecode")
                .withAcquirerId("test_ac_id")
                .withTransactionId(UUID.randomUUID())
                .build();
        //when
        when(transformCommander.transformRefundDto(refundPaymentDto))
                .thenReturn(new BluecodeRefundMessage());
        when(paymentRepository.getById(eq(refundPaymentDto.getTransactionId())))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.refundPayment(any(RefundMessage.class)))
                .thenThrow(new BluecodeFeignInternalException(EXCEPTION_TEST_MESSAGE));

        assertThatExceptionOfType(ApiFeignException.class)
                .isThrownBy(() -> paymentService.refundPayment(refundPaymentDto))
                .withMessage(EXCEPTION_TEST_MESSAGE);

        verify(transformCommander, times(1))
                .transformRefundDto(eq(refundPaymentDto));
        verify(paymentRepository, times(1))
                .getById(eq(refundPaymentDto.getTransactionId()));
        verify(requestCommander, times(1))
                .refundPayment(any(BluecodeRefundMessage.class));
    }

    @Test
    void shouldTriggerDataBaseExceptionRefundPaymentTest() {
        RefundPaymentDto refundPaymentDto = RefundPaymentDto.builder()
                .withApi("bluecode")
                .withAcquirerId("test_ac_id")
                .withTransactionId(UUID.randomUUID())
                .build();
        //when
        when(transformCommander.transformRefundDto(refundPaymentDto))
                .thenReturn(new BluecodeRefundMessage());
        when(paymentRepository.getById(eq(refundPaymentDto.getTransactionId())))
                .thenReturn(null);

        assertThatExceptionOfType(DataBaseNotFoundException.class)
                .isThrownBy(() -> paymentService.refundPayment(refundPaymentDto))
                .withMessage("payment with id [" + refundPaymentDto.getTransactionId() + "]");

        verify(transformCommander, times(1))
                .transformRefundDto(eq(refundPaymentDto));
        verify(paymentRepository, times(1))
                .getById(eq(refundPaymentDto.getTransactionId()));

    }

    @Test
    void getPaymentTest() {
        UUID id = UUID.randomUUID();
        String api = "bluecode";
        TransactionDto dto = TransactionDto.builder()
                .withApi(api)
                .withPaymentId(id).build();
        when(transformCommander.transformPaymentIdDto(dto))
                .thenReturn(new BluecodeTransactionMessage());
        when(requestCommander.statusTransaction(any(TransactionMessage.class)))
                .thenReturn(new BluecodeResponseMessage());

        ResponseMessage responseMessage = paymentService.getPayment(id, api);

        verify(transformCommander, times(1))
                .transformPaymentIdDto(dto);
        verify(requestCommander, times(1))
                .statusTransaction(any(BluecodeTransactionMessage.class));

        assertNotNull(responseMessage);
        assertThat(new BluecodeResponseMessage()).isEqualTo(responseMessage);
    }

    @Test
    void whenTestImplementationGetPaymentTest() {
        UUID id = UUID.randomUUID();
        String api = "bluecode";
        TransactionDto dto = TransactionDto.builder()
                .withApi(api)
                .withPaymentId(id).build();
        when(transformCommander.transformPaymentIdDto(dto))
                .thenReturn(new TransactionMessageTest());
        when(requestCommander.statusTransaction(any(TransactionMessage.class)))
                .thenReturn(new ResponseMessageTest());

        ResponseMessage responseMessage = paymentService.getPayment(id, api);

        verify(transformCommander, times(1))
                .transformPaymentIdDto(dto);
        verify(requestCommander, times(1))
                .statusTransaction(any(TransactionMessageTest.class));

        assertNotNull(responseMessage);
        assertThat(new ResponseMessageTest()).isEqualTo(responseMessage);
    }

    @Test
    void shouldTriggerApiExceptionGetPaymentTest() {
        TransactionDto dto = TransactionDto.builder()
                .withApi("bluecode")
                .withPaymentId(UUID.randomUUID())
                .build();
        //when
        when(transformCommander.transformPaymentIdDto(dto))
                .thenReturn(new BluecodeTransactionMessage());
        when(requestCommander.statusTransaction(any(TransactionMessage.class)))
                .thenThrow(new BluecodeFeignInternalException(EXCEPTION_TEST_MESSAGE));

        assertThatExceptionOfType(ApiFeignException.class)
                .isThrownBy(() -> paymentService.getPayment(dto.getPaymentId(), dto.getApi()))
                .withMessage(EXCEPTION_TEST_MESSAGE);

        verify(transformCommander, times(1))
                .transformPaymentIdDto(dto);
        verify(requestCommander, times(1))
                .statusTransaction(any(BluecodeTransactionMessage.class));
    }

    @Test
    void cancelPaymentTest() {
        UUID id = UUID.randomUUID();
        String api = "amazon";
        TransactionDto dto = TransactionDto.builder()
                .withPaymentId(id)
                .withApi(api)
                .build();

        when(transformCommander.transformPaymentIdDto(dto))
                .thenReturn(new BluecodeTransactionMessage());
        when(paymentRepository.getById(id))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.cancelTransaction(any(TransactionMessage.class)))
                .thenReturn(new BluecodeResponseMessage());
        when(requestCommander.changePaymentState(any(RequestMessage.class), eq("CANCELLED")))
                .thenReturn(new BluecodeRequestMessage());

        ResponseMessage responseMessage = paymentService.cancelPayment(dto);

        verify(transformCommander, times(1))
                .transformPaymentIdDto(dto);
        verify(paymentRepository, times(1))
                .getById(id);
        verify(requestCommander, times(1))
                .cancelTransaction(any(BluecodeTransactionMessage.class));
        verify(requestCommander, times(1))
                .changePaymentState(any(BluecodeRequestMessage.class), eq("CANCELLED"));
        verify(paymentRepository, times(1))
                .save(any(BluecodeRequestMessage.class));

        assertNotNull(responseMessage);
        assertThat(new BluecodeResponseMessage()).isEqualTo(responseMessage);
    }

    @Test
    void whenTestImplementationCancelPaymentTest() {
        UUID id = UUID.randomUUID();
        String api = "amazon";
        TransactionDto dto = TransactionDto.builder()
                .withPaymentId(id)
                .withApi(api)
                .build();

        when(transformCommander.transformPaymentIdDto(dto))
                .thenReturn(new TransactionMessageTest());
        when(paymentRepository.getById(id))
                .thenReturn(new RequestMessageTest());
        when(requestCommander.cancelTransaction(any(TransactionMessage.class)))
                .thenReturn(new ResponseMessageTest());
        when(requestCommander.changePaymentState(any(RequestMessage.class), eq("CANCELLED")))
                .thenReturn(new RequestMessageTest());

        ResponseMessage responseMessage = paymentService.cancelPayment(dto);

        verify(transformCommander, times(1))
                .transformPaymentIdDto(dto);
        verify(paymentRepository, times(1))
                .getById(id);
        verify(requestCommander, times(1))
                .cancelTransaction(any(TransactionMessageTest.class));
        verify(requestCommander, times(1))
                .changePaymentState(any(RequestMessageTest.class), eq("CANCELLED"));
        verify(paymentRepository, times(1))
                .save(any(RequestMessageTest.class));

        assertNotNull(responseMessage);
        assertThat(new ResponseMessageTest()).isEqualTo(responseMessage);
    }

    @Test
    void shouldDataBaseExceptionCancelPaymentTest() {
        UUID id = UUID.randomUUID();
        String api = "amazon";
        TransactionDto dto = TransactionDto.builder()
                .withPaymentId(id)
                .withApi(api)
                .build();


        when(paymentRepository.getById(id))
                .thenReturn(null);

        assertThatExceptionOfType(DataBaseNotFoundException.class)
                .isThrownBy(() -> paymentService.cancelPayment(dto))
                .withMessage("payment with id [" + dto.getPaymentId() + "]");

        verify(transformCommander, times(1))
                .transformPaymentIdDto(dto);
        verify(paymentRepository, times(1))
                .getById(id);

    }

    @Test
    void shouldTriggerApiExceptionCancelPaymentTest() {
        TransactionDto dto = TransactionDto.builder()
                .withApi("bluecode")
                .withPaymentId(UUID.randomUUID())
                .build();
        //when
        when(transformCommander.transformPaymentIdDto(dto))
                .thenReturn(new BluecodeTransactionMessage());
        when(paymentRepository.getById(dto.getPaymentId()))
                .thenReturn(new BluecodeRequestMessage());
        when(requestCommander.cancelTransaction(any(TransactionMessage.class)))
                .thenThrow(new BluecodeFeignInternalException(EXCEPTION_TEST_MESSAGE));

        assertThatExceptionOfType(ApiFeignException.class)
                .isThrownBy(() -> paymentService.cancelPayment(dto))
                .withMessage(EXCEPTION_TEST_MESSAGE);

        verify(transformCommander, times(1))
                .transformPaymentIdDto(dto);
        verify(requestCommander, times(1))
                .cancelTransaction(any(BluecodeTransactionMessage.class));
    }

}
