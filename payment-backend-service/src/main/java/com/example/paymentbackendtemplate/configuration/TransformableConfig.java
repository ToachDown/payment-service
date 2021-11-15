package com.example.paymentbackendtemplate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import template.interfaces.transformable.PaymentTransformable;
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
public class TransformableConfig {

    @Bean
    public <T extends RequestMessage, R extends PaymentDto> Map<String, PaymentTransformable<T, R>> requestTransformMap (
            final List<PaymentTransformable<T, R>> requestTransforms) {

        return requestTransforms.stream()
                .collect(Collectors.toMap(PaymentTransformable::getType, identity()));
    }

    @Bean
    public <T extends RefundMessage, R extends RefundPaymentDto> Map<String, PaymentTransformable<T, R>> refundTransformMap (
            final List<PaymentTransformable<T, R>> refundTransforms
    ) {
        return refundTransforms.stream()
                .collect(Collectors.toMap(PaymentTransformable::getType, identity()));
    }

    @Bean
    public <T extends TransactionMessage, R extends TransactionDto> Map<String, PaymentTransformable<T, R>> TxIdTransformMap (
            final List<PaymentTransformable<T, R>> refundTransforms
    ) {
        return refundTransforms.stream()
                .collect(Collectors.toMap(PaymentTransformable::getType, identity()));
    }
}
