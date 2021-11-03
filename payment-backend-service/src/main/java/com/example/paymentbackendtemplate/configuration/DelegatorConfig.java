package com.example.paymentbackendtemplate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import template.interfaces.PaymentResolver;
import template.model.RequestMessage;
import template.model.ResponseMessage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;


@Configuration
public class DelegatorConfig {

    @Bean
    public <T extends RequestMessage, R extends ResponseMessage> Map<String, PaymentResolver<T, R>> requestDelegatorMap (
            final List<PaymentResolver<T, R>> requestDelegators ) {

        return requestDelegators.stream()
                .collect(Collectors.toMap(PaymentResolver::getType, identity()));
    }

}
