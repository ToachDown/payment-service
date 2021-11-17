package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class ResponseBluecodeOk extends ResponseMessageBluecode{

    @JsonProperty("payment")
    private Payment payment;
    @JsonProperty("instant_refund")
    private InstantRefund instantRefund;
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    @Enumerated(EnumType.STRING)
    private final Result result = Result.OK;

}
