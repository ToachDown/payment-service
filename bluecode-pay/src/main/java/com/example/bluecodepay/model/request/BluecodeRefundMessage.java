package com.example.bluecodepay.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import template.model.RefundMessage;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class BluecodeRefundMessage extends RefundMessage {

    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("reason")
    private String reason;

}
