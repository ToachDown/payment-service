package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Code;
import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.example.bluecodepay.model.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Payment {

    @JsonProperty("state")
    private State state;
    @JsonProperty("merchant_tx_id")
    private String merchantIxId;
    @JsonProperty("code")
    private Code code;
    @JsonProperty("acquirer_tx_id")
    private String acquirerTxId;
    @JsonProperty("scheme")
    private Scheme scheme;
    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("end_to_end_id")
    private String endToEndId;
    @JsonProperty("slip_note")
    private String slipNote;
    @JsonProperty("total_amount")
    private int totalAmount;
    @JsonProperty("requested_amount")
    private int requestedAmount;
    @JsonProperty("consumer_tip_amount")
    private int consumerTipAmount;
}
