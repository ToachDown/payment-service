package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Code;
import com.example.bluecodepay.model.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class PaymentFailure extends Payment {

    @JsonProperty("state")
    @Enumerated(EnumType.STRING)
    private final State state = State.FAILURE;
    @JsonProperty("code")
    @Enumerated(EnumType.STRING)
    private Code code;
    @JsonProperty("acquirer_tx_id")
    private String acquirerTxId;
}
