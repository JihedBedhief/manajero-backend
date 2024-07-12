package manajero.manajerotdddynamicintro.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "step")
public class Step {
    @Id
    private String id;
    private String label;
    private String title;
    private String description;
}
