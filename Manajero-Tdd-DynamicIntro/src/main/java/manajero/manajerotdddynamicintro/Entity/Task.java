package manajero.manajerotdddynamicintro.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "task")
public class Task {
    @Id
    private String id ;
    private String name;
    private String project;
    private String assigned;
    private String description;
    private Date dueDate;
    private String status;
    private String comments;
}
