package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Result;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import template.model.ResponseMessage;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "result")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResponseBluecodeOk.class, name = "OK"),
        @JsonSubTypes.Type(value = ResponseBluecodeProcessing.class, name = "PROCESSING"),
        @JsonSubTypes.Type(value = ResponseBluecodeError.class, name = "ERROR")
})
public class ResponseMessageBluecode extends ResponseMessage {

    @JsonProperty("result")
    private Result result;

}
