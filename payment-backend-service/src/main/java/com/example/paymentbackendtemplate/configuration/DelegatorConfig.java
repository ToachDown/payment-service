package com.example.paymentbackendtemplate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import template.interfaces.PaymentResolver;
import template.interfaces.PaymentTransformable;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TxIdMessage;
import template.model.dto.PaymentDto;
import template.model.dto.PaymentIdDto;
import template.model.dto.RefundPaymentDto;

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
    public <T extends TxIdMessage, R extends PaymentIdDto> Map<String, PaymentTransformable<T, R>> TxIdTransformMap (
            final List<PaymentTransformable<T, R>> refundTransforms
    ) {
        return refundTransforms.stream()
                .collect(Collectors.toMap(PaymentTransformable::getType, identity()));
    }

}
