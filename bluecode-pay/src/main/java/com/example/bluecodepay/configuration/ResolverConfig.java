package com.example.bluecodepay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import template.interfaces.PaymentResolver;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Configuration
@ComponentScan("com.example.bluecodepay")
public class ResolverConfig {

    @Bean
    public <R extends RequestMessage,
            T extends RefundMessage,
            V extends TransactionMessage,
            G extends ResponseMessage>
    Map<String, PaymentResolver<R, T, V, G>> paymentDelegatorMap(final List<PaymentResolver<R, T, V, G>> paymentResolvers) {
        return paymentResolvers.stream()
                .collect(Collectors.toMap(PaymentResolver::getType, identity()));
    }
}
