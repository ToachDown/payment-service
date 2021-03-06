package com.example.bluecodepay.factory;

import com.example.bluecodepay.model.enums.Result;
import com.example.bluecodepay.model.response.BluecodeResponseMessage;
import com.example.bluecodepay.model.response.Status;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Resilience4jFactory {

    public Retry getConfigureRetry(Status status) {
        RetryConfig config = RetryConfig.<BluecodeResponseMessage>custom()
                .waitDuration(Duration.ofMillis(status.getCheckStatusIn()))
                .maxAttempts(status.getTtl() / status.getCheckStatusIn() + 3)
                .retryOnResult(response -> response.getResult().equals(Result.PROCESSING))
                .build();
        RetryRegistry registry = RetryRegistry.of(config);
        return registry.retry("ProcessingRetry");
    }
}
