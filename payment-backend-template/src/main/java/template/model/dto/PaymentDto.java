package template.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    @NotNull
    private String api;

    @NotNull
    private Integer amount;

    private int discountAmount;

    @NotNull
    private String currency;

    @NotNull
    private String source;

    @NotNull
    private boolean instantPayment;

    private String description;

    private String transactionId;
}
