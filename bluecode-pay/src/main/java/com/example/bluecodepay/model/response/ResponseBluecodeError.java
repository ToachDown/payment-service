package com.example.bluecodepay.model.response;

import com.example.bluecodepay.model.enums.ErrorCode;
import com.example.bluecodepay.model.enums.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class ResponseBluecodeError extends ResponseMessageBluecode{

    @JsonProperty("error_code")
    private ErrorCode errorCode;

}
