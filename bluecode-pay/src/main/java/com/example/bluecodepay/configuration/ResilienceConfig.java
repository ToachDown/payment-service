package com.example.bluecodepay.configuration;

import com.example.bluecodepay.model.enums.Result;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import com.example.bluecodepay.model.response.Status;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ResilienceConfig {

    public Retry getConfigureRetry(Status status) {
        RetryConfig config = RetryConfig.<ResponseMessageBluecode>custom()
                .waitDuration(Duration.ofMillis(status.getCheckStatusIn()))
                .maxAttempts(status.getTtl() / status.getCheckStatusIn() + 3)
                .retryOnResult(response -> response.getResult().equals(Result.PROCESSING))
                .build();
        RetryRegistry registry = RetryRegistry.of(config);
        return registry.retry("my");
    }
}
