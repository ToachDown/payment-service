package com.example.paymentbackendtemplate.implementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import template.model.TransactionMessage;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class TransactionMessageTest extends TransactionMessage {

    public TransactionMessageTest() {
    }
}
