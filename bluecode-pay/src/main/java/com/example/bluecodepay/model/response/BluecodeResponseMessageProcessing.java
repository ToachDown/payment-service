package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class BluecodeResponseMessageProcessing extends BluecodeResponseMessage {

    @JsonProperty("result")
    @Enumerated(EnumType.STRING)
    private final Result result = Result.PROCESSING;
    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
