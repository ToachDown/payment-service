package com.example.bluecodepay.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class Status {

    @JsonProperty("merchant_tx_id")
    private String merchantTxId;
    @JsonProperty("check_status_in")
    private int checkStatusIn;
    @JsonProperty("ttl")
    private int ttl;
}
