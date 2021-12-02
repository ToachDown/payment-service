package com.example.bluecodepay.configuration;

import com.example.backendtemplate.interfaces.PaymentAwareTransformer;
import com.example.backendtemplate.model.RefundMessage;
import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.TransactionMessage;
import com.example.backendtemplate.model.dto.PaymentDto;
import com.example.backendtemplate.model.dto.RefundPaymentDto;
import com.example.backendtemplate.model.dto.TransactionDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
