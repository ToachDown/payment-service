package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Code;
import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.example.bluecodepay.model.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class PaymentApproved extends Payment {

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
    @JsonProperty("merchant_callback_url")
    private String merchantCallbackUrl;
    @JsonProperty("state")
    private final State state = State.APPROVED;
}
