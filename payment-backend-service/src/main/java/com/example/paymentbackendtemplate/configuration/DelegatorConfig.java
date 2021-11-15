package com.example.paymentbackendtemplate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import template.interfaces.resolver.*;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;


@Configuration
public class DelegatorConfig {

    @Bean
    public <T extends RequestMessage, R extends ResponseMessage> Map<String, StartPaymentResolver<T, R>> startRequestDelegatorMap (
            final List<StartPaymentResolver<T, R>> startRequestDelegators ) {

        return startRequestDelegators.stream()
                .collect(Collectors.toMap(StartPaymentResolver::getType, identity()));
    }

    @Bean
    public <T extends RequestMessage, R extends ResponseMessage> Map<String, UpdatePaymentResolver<T, R>> updateRequestDelegatorMap (
            final List<UpdatePaymentResolver<T, R>> updateRequestDelegators ) {

        return updateRequestDelegators.stream()
                .collect(Collectors.toMap(UpdatePaymentResolver::getType, identity()));
    }

    @Bean
    public <T extends RequestMessage, R extends ResponseMessage> Map<String, CapturePaymentResolver<T, R>> captureRequestDelegatorMap (
            final List<CapturePaymentResolver<T, R>> captureRequestDelegators ) {

        return captureRequestDelegators.stream()
                .collect(Collectors.toMap(CapturePaymentResolver::getType, identity()));
    }

    @Bean
    public <T extends TransactionMessage, R extends ResponseMessage> Map<String, StatusPaymentResolver<T, R>> statusPaymentDelegatorMap(
            final List<StatusPaymentResolver<T, R>> statusPaymentResolvers
    ) {
        return statusPaymentResolvers.stream()
                .collect(Collectors.toMap(StatusPaymentResolver::getType, identity()));
    }

    @Bean
    public <T extends TransactionMessage, R extends ResponseMessage> Map<String, CancelPaymentResolver<T, R>> cancelPaymentDelegatorMap(
            final List<CancelPaymentResolver<T, R>> cancelPaymentResolvers
    ) {
        return cancelPaymentResolvers.stream()
                .collect(Collectors.toMap(CancelPaymentResolver::getType, identity()));
    }

    @Bean
    public <T extends RefundMessage, R extends ResponseMessage> Map<String, RefundPaymentResolver<T, T>> refundPaymentResolverMap(
            final List<RefundPaymentResolver<T, T>> refundPaymentResolvers
    ) {
        return refundPaymentResolvers.stream()
                .collect(Collectors.toMap(RefundPaymentResolver::getType, identity()));
    }

}
