package template.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class RefundPaymentDto {

    @NonNull
    private String api;

    @NonNull
    private String acquirerTransactionId;

    @NonNull
    private Integer amount;

    private String reason;
}
