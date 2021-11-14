package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Result;
import com.example.bluecodepay.model.enums.State;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class ResponseBluecodeProcessing extends ResponseMessageBluecode{

    @JsonProperty("status")
    private Status status;

}
