package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Result;
import com.example.bluecodepay.model.enums.State;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class ResponseBluecodeProcessing extends ResponseMessageBluecode{

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonProperty("result")
    @Enumerated(EnumType.STRING)
    private final Result result = Result.PROCESSING;

}
