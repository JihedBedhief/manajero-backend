package manajero.manajerotdddynamicintro.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document(collection = "what")
public class What {
    private String id ;
    private String description ;
}
