package com.example.bluecodepay.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import template.model.RefundRequest;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class RefundBluecodeRequest extends RefundRequest {

    @JsonProperty("acquirer_tx_id")
    private String acquirerTxId;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("reason")
    private String reason;

}
