package com.example.bluecodepay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import template.interfaces.PaymentAwareTransformer;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.TransactionMessage;
import template.model.dto.PaymentDto;
import template.model.dto.RefundPaymentDto;
import template.model.dto.TransactionDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Configuration
@ComponentScan("com.example.bluecodepay")
public class TransformerConfig {

    @Bean
    public <T extends RequestMessage, R extends PaymentDto> Map<String, PaymentAwareTransformer<T, R>> requestTransformMap(
            final List<PaymentAwareTransformer<T, R>> requestTransforms) {

        return requestTransforms.stream()
                .collect(Collectors.toMap(PaymentAwareTransformer::getType, identity()));
    }

    @Bean
    public <T extends RefundMessage, R extends RefundPaymentDto> Map<String, PaymentAwareTransformer<T, R>> refundTransformMap(
            final List<PaymentAwareTransformer<T, R>> refundTransforms
    ) {
        return refundTransforms.stream()
                .collect(Collectors.toMap(PaymentAwareTransformer::getType, identity()));
    }

    @Bean
    public <T extends TransactionMessage, R extends TransactionDto> Map<String, PaymentAwareTransformer<T, R>> TxIdTransformMap(
            final List<PaymentAwareTransformer<T, R>> refundTransforms
    ) {
        return refundTransforms.stream()
                .collect(Collectors.toMap(PaymentAwareTransformer::getType, identity()));
    }
}
