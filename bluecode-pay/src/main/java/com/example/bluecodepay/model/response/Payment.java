package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.State;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "state")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaymentFailure.class, name = "FAILURE"),
        @JsonSubTypes.Type(value = PaymentDeclined.class, name = "DECLINED"),
        @JsonSubTypes.Type(value = PaymentApproved.class, name = "APPROVED"),
        @JsonSubTypes.Type(value = PaymentCanceled.class, name = "CANCELLED")
})
public abstract class Payment {

    @JsonProperty("state")
    @Enumerated(EnumType.STRING)
    private State state;

}
