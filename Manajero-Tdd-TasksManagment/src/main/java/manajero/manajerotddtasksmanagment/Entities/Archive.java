package manajero.manajerotddtasksmanagment.Entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "archive")
@Getter
@Setter
public class Archive {

    @Id
    private String id;
    private List<Project> projects;
    private List<Tests> tsts;
    private List<Task> tasks;
}
