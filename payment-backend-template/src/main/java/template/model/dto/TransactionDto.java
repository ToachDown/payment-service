package template.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class TransactionDto {

    @NotNull
    @Size(min = 3, max = 128)
    private String api;

    @NotNull
    private UUID PaymentId;
}
