package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.ErrorCode;
import com.example.bluecodepay.model.enums.Result;
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
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class BluecodeResponseErrorResponseMessage extends BluecodeResponseMessage {

    @JsonProperty("result")
    @Enumerated(EnumType.STRING)
    private final Result result = Result.ERROR;
    @JsonProperty("error_code")
    @Enumerated(EnumType.STRING)
    private ErrorCode errorCode;

}
