package com.example.bluecodepay.configuration;

import com.example.backendtemplate.interfaces.PaymentResolver;
import com.example.backendtemplate.model.RefundMessage;
import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.ResponseMessage;
import com.example.backendtemplate.model.TransactionMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Configuration
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
