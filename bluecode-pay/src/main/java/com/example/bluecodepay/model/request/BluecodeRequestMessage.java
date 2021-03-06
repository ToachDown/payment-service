package com.example.bluecodepay.model.request;

import com.example.backendtemplate.model.RequestMessage;
import com.example.bluecodepay.model.enums.Currency;
import com.example.bluecodepay.model.enums.Scheme;
import com.example.bluecodepay.model.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
@Table(name = "payment_bluecode")
public class BluecodeRequestMessage extends RequestMessage {

    @JsonProperty("scheme")
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
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
    @JsonProperty("consumer_tip_amount")
    private int tipAmount;
    @JsonProperty("discount_amount")
    private int discountAmount;
    @JsonProperty("requested_amount")
    private int requestedAmount;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private State state;
}
