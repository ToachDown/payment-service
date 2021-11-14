package template.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PaymentIdDto {

    @NonNull
    private String api;

    @NonNull
    private String TransactionId;
}
