package com.example.paymentbackendtemplate.integration;

import com.example.backendtemplate.model.RequestMessage;
import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.example.bluecodepay.model.enums.Source;
import com.example.bluecodepay.model.enums.State;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.paymentbackendtemplate.implementation.RequestMessageTest;
import com.example.paymentbackendtemplate.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@Transactional
public class RepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13-alpine")
            .withUsername("postgres")
            .withPassword("test")
            .withDatabaseName("Payment");
    @Autowired
    private PaymentRepository paymentRepository;

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeEach
    public void setUp() {
        RequestMessage requestMessageTest = RequestMessageTest.builder()
                .withTestField("test")
                .withType("test")
                .build();
        RequestMessage requestMessage1 = BluecodeRequestMessage.builder()
                .withBarcode("98802222100100123456")
                .withBranchExtId("test branch")
                .withCurrency(Currency.EUR)
                .withDiscountAmount(100)
                .withEndToEndId("test desc")
                .withPurchaseAmount(1900)
                .withRequestedAmount(1860)
                .withScheme(Scheme.AUTO)
                .withSource(Source.VENDING.name())
                .withState(State.TODO)
                .withTipAmount(60)
                .withType("bluecode")
                .build();
        RequestMessage requestMessage2 = BluecodeRequestMessage.builder()
                .withBarcode("98800000000000000100")
                .withBranchExtId("test branch")
                .withCurrency(Currency.EUR)
                .withDiscountAmount(100)
                .withEndToEndId("test desc")
                .withPurchaseAmount(1900)
                .withRequestedAmount(1860)
                .withScheme(Scheme.AUTO)
                .withSource(Source.VENDING.name())
                .withState(State.TODO)
                .withTipAmount(60)
                .withType("bluecode")
                .build();
        RequestMessage requestMessage3 = BluecodeRequestMessage.builder()
                .withBarcode("98802222999900301000")
                .withBranchExtId("test branch")
                .withCurrency(Currency.EUR)
                .withDiscountAmount(100)
                .withEndToEndId("test desc")
                .withPurchaseAmount(122900)
                .withRequestedAmount(12860)
                .withScheme(Scheme.AUTO)
                .withSource(Source.VENDING.name())
                .withState(State.TODO)
                .withTipAmount(620)
                .withType("bluecode")
                .build();
        RequestMessage requestMessage4 = BluecodeRequestMessage.builder()
                .withBarcode("98804444000000402005")
                .withBranchExtId("test branch")
                .withCurrency(Currency.BYN)
                .withDiscountAmount(100)
                .withEndToEndId("test desc")
                .withPurchaseAmount(19200)
                .withRequestedAmount(1860)
                .withScheme(Scheme.AUTO)
                .withSource(Source.VENDING.name())
                .withState(State.TODO)
                .withTipAmount(60)
                .withType("bluecode")
                .build();
        paymentRepository.save(requestMessage1);
        paymentRepository.save(requestMessageTest);
        paymentRepository.save(requestMessage2);
        paymentRepository.save(requestMessage3);
        paymentRepository.save(requestMessage4);
        paymentRepository.flush();
    }

    @Test
    void containerRunningTest() {
        assertTrue(container.isRunning());
    }

    @Test
    void savePaymentTest() {
        RequestMessage requestMessage = BluecodeRequestMessage.builder()
                .withBarcode("98802222100100123456")
                .withBranchExtId("test branch")
                .withCurrency(Currency.EUR)
                .withDiscountAmount(100)
                .withEndToEndId("test desc")
                .withPurchaseAmount(1900)
                .withRequestedAmount(1860)
                .withScheme(Scheme.AUTO)
                .withSource(Source.VENDING.name())
                .withState(State.TODO)
                .withTipAmount(60)
                .withType("bluecode")
                .build();

        RequestMessage saveRequest = paymentRepository.save(requestMessage);

        assertNotNull(saveRequest);
//        assertThat(any(saveRequest.getClass())).isEqualTo(any(BluecodeRequestMessage.class));
        assertEquals(saveRequest.getType(), "bluecode");
        assertNotNull(saveRequest.getId());
    }

    @Test
    void whenTestImplementationSavePaymentTest() {
        RequestMessage requestMessage = RequestMessageTest.builder()
                .withTestField("test filed")
                .withType("test")
                .build();

        RequestMessage saveRequest = paymentRepository.save(requestMessage);

        assertNotNull(saveRequest);
        //    assertThat(any(saveRequest.getClass())).isEqualTo(any(RequestMessageTest.class));
        assertEquals(saveRequest.getType(), "test");
        assertNotNull(saveRequest.getId());
    }

    @Test
    void saveAndFlushPaymentTest() {
        RequestMessage requestMessage = BluecodeRequestMessage.builder()
                .withBarcode("98802222100100123456")
                .withBranchExtId("test branch")
                .withCurrency(Currency.EUR)
                .withDiscountAmount(100)
                .withEndToEndId("test desc")
                .withPurchaseAmount(1900)
                .withRequestedAmount(1860)
                .withScheme(Scheme.AUTO)
                .withSource(Source.VENDING.name())
                .withState(State.TODO)
                .withTipAmount(60)
                .withType("bluecode")
                .build();

        RequestMessage saveRequest = paymentRepository.saveAndFlush(requestMessage);

        assertNotNull(saveRequest);
//        assertThat(any(saveRequest.getClass())).isEqualTo(any(BluecodeRequestMessage.class));
        assertEquals(saveRequest.getType(), "bluecode");
        assertNotNull(saveRequest.getId());
    }

    @Test
    void getAllTest() {
        List<RequestMessage> payments = paymentRepository.findAll();


        assertFalse(payments.isEmpty());
        assertEquals(5, payments.size());
        payments.forEach(payment -> {
//            assertThat(any(payment.getClass())).isEqualTo(any(BluecodeRequestMessage.class));
            assertNotNull(payment);
            assertNotNull(payment.getId());
        });
    }

    @Test
    void findAllByTypeTest() {
        List<RequestMessage> bluecodePayments = paymentRepository.findAllByType("bluecode");

        assertFalse(bluecodePayments.isEmpty());
        assertEquals(4, bluecodePayments.size());
        bluecodePayments.forEach(payment -> {
            assertEquals("bluecode", payment.getType());
            assertNotNull(payment.getId());
        });

        List<RequestMessage> testPayments = paymentRepository.findAllByType("test");

        assertFalse(testPayments.isEmpty());
        assertEquals(1, testPayments.size());
        testPayments.forEach(payment -> {
//            assertThat(any(payment.getClass())).isEqualTo(any(RequestMessageTest.class));
            assertEquals("test", payment.getType());
            assertNotNull(payment.getId());
        });
    }

    @Test
    void whenTypeNotExistsFindAllByTypeTest() {
        List<RequestMessage> bluecodePayments = paymentRepository.findAllByType("exception");

        assertTrue(bluecodePayments.isEmpty());
    }

    @Test
    void getByIdTest() {
        RequestMessage requestMessageTest = RequestMessageTest.builder()
                .withTestField("test_second")
                .withType("test")
                .build();

        RequestMessage requestMessage = paymentRepository.saveAndFlush(requestMessageTest);
        RequestMessage resultRequestMessage = paymentRepository.getById(requestMessage.getId());

        assertNotNull(resultRequestMessage);
        //    assertThat(any(resultRequestMessage.getClass())).isEqualTo(any(RequestMessageTest.class));
        assertNotNull(requestMessage.getId());
        assertEquals("test", requestMessageTest.getType());
    }

    @Test
    void deleteByIdTest() {
        RequestMessage requestMessageTest = RequestMessageTest.builder()
                .withTestField("test_second")
                .withType("test")
                .build();
        RequestMessage requestMessage = paymentRepository.saveAndFlush(requestMessageTest);
        int startSize = paymentRepository.findAll().size();
        paymentRepository.deleteById(requestMessage.getId());
        int endSize = paymentRepository.findAll().size();
        assertEquals(startSize - 1, endSize);
    }

}
