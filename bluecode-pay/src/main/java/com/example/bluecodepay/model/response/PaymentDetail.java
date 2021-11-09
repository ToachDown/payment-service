package com.example.bluecodepay.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class PaymentDetail extends ResponseMessageBluecode{

    @JsonProperty("state")
    private String state;
    @JsonProperty("merchant_tx_id")
    private String merchantIxId;
    @JsonProperty("acquirer_tx_id")
    private String acquirerTxId;
    @JsonProperty("scheme")
    private String scheme;
    @JsonProperty("currency")
    private String currency;
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
