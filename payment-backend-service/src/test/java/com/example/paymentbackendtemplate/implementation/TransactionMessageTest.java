package com.example.paymentbackendtemplate.implementation;

import com.example.backendtemplate.model.TransactionMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class TransactionMessageTest extends TransactionMessage {

    public TransactionMessageTest() {
    }
}
