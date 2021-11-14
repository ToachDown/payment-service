package com.example.bluecodepay.model.request;

import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import template.model.RequestMessage;

import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class RequestMessageBluecode extends RequestMessage {

    @JsonProperty("merchant_tx_id")
    private String merchantTxId;
    @JsonProperty("scheme")
    private Scheme scheme;
    @JsonProperty("slip_date_time")
    private LocalDate slipDateTime;
    @JsonProperty("slip_note")
    private String slipNote;
    @JsonProperty("barcode")
    private String barcode;
    @JsonProperty("branch_ext_id")
    private String branchExtId;
    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("slip")
    private String slip;
    @JsonProperty("source")
    private String source;
    @JsonProperty("terminal")
    private String terminal;
    @JsonProperty("entry_mode")
    private String entryMode;
    @JsonProperty("end_to_end_id")
    private String endToEndId;
    @JsonProperty("purchase_amount")
    private int purchaseAmount;
    @JsonProperty("tip_amount")
    private int tipAmount;
    @JsonProperty("discount_amount")
    private int discountAmount;
    @JsonProperty("requested_amount")
    private int requestedAmount;

}
