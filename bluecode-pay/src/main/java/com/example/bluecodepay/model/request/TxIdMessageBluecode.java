package com.example.bluecodepay.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import template.model.TxIdMessage;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class TxIdMessageBluecode extends TxIdMessage {

    @JsonProperty("merchant_tx_id")
    private String TransactionId;
}
