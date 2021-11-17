package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.Result;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import template.model.ResponseMessage;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "result")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResponseBluecodeOk.class, name = "OK"),
        @JsonSubTypes.Type(value = ResponseBluecodeProcessing.class, name = "PROCESSING"),
        @JsonSubTypes.Type(value = ResponseBluecodeError.class, name = "ERROR")
})
public abstract class ResponseMessageBluecode extends ResponseMessage {

    @JsonProperty("result")
    @Enumerated(EnumType.STRING)
    private Result result;
    @JsonProperty("type")
    private final String type = "bluecode";

}
