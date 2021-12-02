package com.example.bluecodepay.configuration;

import com.example.backendtemplate.interfaces.PaymentUpdater;
import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.ResponseMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Configuration
@ComponentScan("com.example.bluecodepay")
public class PaymentUpdaterConfig {

    @Bean
    public <T extends RequestMessage, R extends ResponseMessage> Map<String, PaymentUpdater<T, R>> paymentStateChangerMap(
            final List<PaymentUpdater<T, R>> stateChangers
    ) {
        return stateChangers.stream()
                .collect(Collectors.toMap(PaymentUpdater::getType, identity()));
    }

}
