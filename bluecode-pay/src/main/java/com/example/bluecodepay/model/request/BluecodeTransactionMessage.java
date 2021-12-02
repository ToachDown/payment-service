package com.example.bluecodepay.model.request;

import com.example.backendtemplate.model.TransactionMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class BluecodeTransactionMessage extends TransactionMessage {

    @JsonProperty("merchant_tx_id")
    private UUID TransactionId;
}
