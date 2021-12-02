package com.example.bluecodepay.model.response;

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
public class PaymentCanceled extends Payment {

    @JsonProperty("state")
    @Enumerated(EnumType.STRING)
    private final State state = State.CANCELLED;
    @JsonProperty("merchant_tx_id")
    private String merchantIxId;

}
