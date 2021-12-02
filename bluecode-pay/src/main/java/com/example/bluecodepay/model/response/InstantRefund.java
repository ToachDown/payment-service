package com.example.bluecodepay.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class InstantRefund {

    @JsonProperty("end_to_end_id")
    private String endToEndId;
    @JsonProperty("refundable_amount")
    private int refundableAmount;

}
