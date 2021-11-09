package template.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.annotation.processing.Generated;
import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
public class RequestMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @JsonProperty("type")
    private String type;

}
