package manajero.manajerotdddynamicintro.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "accordion")
public class Accordion {

    @Id
    private String id ;
    private String title;
    private List<String> content;
}
