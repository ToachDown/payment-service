package com.example.backendtemplate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, setterPrefix = "with")
public class RefundPaymentDto {

    @NotNull
    @Size(max = 128, min = 3)
    private String api;

    @NotNull
    private String acquirerId;

    @NotNull
    private UUID transactionId;

    @NotNull
    @Min(0)
    private Integer amount;

    @Size(max = 1024)
    private String reason;
}
