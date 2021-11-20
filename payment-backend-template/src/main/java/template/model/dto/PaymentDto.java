package template.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    @NotNull
    @Size(max = 128, min = 3)
    private String api;

    @NotNull
    @Min(0)
    private Integer amount;

    @Min(0)
    private int discountAmount;

    @NotNull
    @Size(min = 3, max = 3)
    private String currency;

    @NotNull
    @Size(min = 3, max = 24)
    private String source;

    @NotNull
    private boolean instantPayment;

    @Size(max = 1024)
    private String description;

}
