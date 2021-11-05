package template.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
}
