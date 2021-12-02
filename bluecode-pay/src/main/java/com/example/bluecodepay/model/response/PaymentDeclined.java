package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Code;
import com.example.bluecodepay.model.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class PaymentDeclined extends Payment {

    @JsonProperty("state")
    private final State state = State.DECLINED;
    @JsonProperty("code")
    private Code code;
    @JsonProperty("acquirer_tx_id")
    private String acquirerTxId;

}
