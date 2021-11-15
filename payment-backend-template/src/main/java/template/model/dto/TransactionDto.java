package template.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TransactionDto {

    @NotNull
    @Size(min = 3, max = 128)
    private String api;

    @NotNull
    private String TransactionId;
}
