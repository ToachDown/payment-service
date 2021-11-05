package template.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
public class RequestMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String type;

    @JsonCreator
    public RequestMessage(String type) {
        this.type = type;
    }

}
