package template.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RefundPaymentDto {

    @NotNull
    @Size(max = 128, min =3)
    private String api;

    @NotNull
    private String acquirerTransactionId;

    @NotNull
    @Min(0)
    private Integer amount;

    @Size(max = 1024)
    private String reason;
}
