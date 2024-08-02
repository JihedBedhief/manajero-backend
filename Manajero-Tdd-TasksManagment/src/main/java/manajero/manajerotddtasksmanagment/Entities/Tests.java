package manajero.manajerotddtasksmanagment.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "test")
public class Tests {

    @Id
    private String id ;
    private String title;
    private String description;
    private Boolean status;
}
