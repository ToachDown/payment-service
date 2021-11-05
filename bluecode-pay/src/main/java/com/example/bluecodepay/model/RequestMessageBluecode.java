package com.example.bluecodepay.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import template.model.RequestMessage;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@JsonTypeName("requestBluecode")
public class RequestMessageBluecode extends RequestMessage {

    private String merchantExtId;
    private String barcode;
    private int requestAmount;

    @JsonCreator
    public RequestMessageBluecode(@JsonProperty("merchantExtId") String merchantExtId,
                                  @JsonProperty("barcode")  String barcode,
                                  @JsonProperty("requestAmount")  int requestAmount) {
        super("bluecode");
        this.merchantExtId = merchantExtId;
        this.barcode = barcode;
        this.requestAmount = requestAmount;
    }
}
