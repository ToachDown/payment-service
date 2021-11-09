package com.example.bluecodepay.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import template.model.ResponseMessage;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "result")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaymentDetail.class, name = "OK"),
        @JsonSubTypes.Type(value = StatusDetail.class, name = "PROCESSING")
})
public class ResponseMessageBluecode extends ResponseMessage {

    @JsonProperty("result")
    private String result;
}
