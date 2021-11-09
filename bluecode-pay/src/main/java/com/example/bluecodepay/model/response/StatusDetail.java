package com.example.bluecodepay.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class StatusDetail extends ResponseMessageBluecode{

    @JsonProperty("merchant_tx_id")
    private String merchantTxId;
    @JsonProperty("check_status_in")
    private int checkStatusIn;
    @JsonProperty("ttl")
    private int ttl;
}
