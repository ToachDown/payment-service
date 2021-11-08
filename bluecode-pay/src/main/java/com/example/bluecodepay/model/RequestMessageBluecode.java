package com.example.bluecodepay.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.springframework.context.annotation.Primary;
import template.model.RequestMessage;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@JsonTypeName("requestBluecode")
public class RequestMessageBluecode extends RequestMessage {

    private String merchant_tx_id;
    private String scheme;
    private LocalDate slip_date_time;
    private String slip_note;
    private String barcode;
    private String branch_ext_id;
    private String currency;
    private String slip;
    private String source;
    private String terminal;
    private String entry_mode;
    private String end_to_end_id;
    private int purchase_amount;
    private int tip_amount;
    private int discount_amount;
    private int requested_amount;

    @JsonCreator
    public RequestMessageBluecode(@JsonProperty("slip_note") String slipNote,
                                  @JsonProperty("slip") String slip,
                                  @JsonProperty("terminal") String terminal,
                                  @JsonProperty("source") String source,
                                  @JsonProperty("entry_mode") String entry_mode,
                                  @JsonProperty("end_to_end_id") String end_to_end_id,
                                  @JsonProperty("barcode") String barcode,
                                  @JsonProperty("discount_amount") int discountAmount,
                                  @JsonProperty("purchase_amount") int purchaseAmount) {
        super("bluecode");
        this.scheme = "AUTO";
        this.slip_date_time = LocalDate.now();
        this.currency = "EUR";
        this.branch_ext_id = UUID.nameUUIDFromBytes("bluecode".getBytes(StandardCharsets.UTF_8)).toString();
        this.slip_note = slipNote;
        this.entry_mode = entry_mode;
        this.slip = slip;
        this.end_to_end_id = end_to_end_id;
        this.source = source;
        this.barcode = barcode;
        this.terminal = terminal;
        this.merchant_tx_id = UUID.randomUUID().toString();
        this.purchase_amount = purchaseAmount;
        this.tip_amount = 5;
        this.discount_amount = discountAmount;
        this.requested_amount = calculatingClearAmount(this.purchase_amount, this.tip_amount, this.discount_amount);
    }

    private int calculatingClearAmount (int full, int tip, int discount) {
        return (full - discount) + (int) ((full - discount) * ( tip/((float) 100)));
    }

}
