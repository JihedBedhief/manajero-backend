package manajero.manajerotddtasksmanagment.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter @Setter
@Document(collection = "Project")
public class Project {

    @Id
    private String id ;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;


}
