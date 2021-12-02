package com.example.bluecodepay.model.response;

import com.example.backendtemplate.model.ResponseMessage;
import com.example.bluecodepay.model.enums.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "result")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BluecodeResponseMessageOk.class, name = "OK"),
        @JsonSubTypes.Type(value = BluecodeResponseMessageProcessing.class, name = "PROCESSING"),
        @JsonSubTypes.Type(value = BluecodeResponseMessageError.class, name = "ERROR")
})
public class BluecodeResponseMessage extends ResponseMessage {

    @JsonProperty("type")
    private final String type = "bluecode";

    @JsonProperty("result")
    @Enumerated(EnumType.STRING)
    private Result result;

}
