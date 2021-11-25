package com.example.bluecodepay.service;

import com.example.bluecodepay.factory.Resilience4jFactory;
import com.example.bluecodepay.model.response.Status;
import io.github.resilience4j.retry.Retry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({MockitoExtension.class})
public class Resilience4jFactoryTest {

    @InjectMocks
    private Resilience4jFactory resilience4jFactory;

    @Test
    void getConfigureRetryTest() {
        Status status = Status.builder()
                .merchantTxId(UUID.randomUUID().toString())
                .checkStatusIn(100)
                .ttl(30000)
                .build();

        Retry retry = resilience4jFactory.getConfigureRetry(status);

        assertThat(retry.getName()).isEqualTo("ProcessingRetry");
        assertThat(retry.getRetryConfig().getMaxAttempts()).isEqualTo(status.getTtl()/status.getCheckStatusIn() + 3);
    }

}
