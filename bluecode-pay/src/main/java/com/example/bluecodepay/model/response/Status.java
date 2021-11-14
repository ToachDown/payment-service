package com.example.bluecodepay.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Status {

    @JsonProperty("merchant_tx_id")
    private String merchantTxId;
    @JsonProperty("check_status_in")
    private int checkStatusIn;
    @JsonProperty("ttl")
    private int ttl;
}
