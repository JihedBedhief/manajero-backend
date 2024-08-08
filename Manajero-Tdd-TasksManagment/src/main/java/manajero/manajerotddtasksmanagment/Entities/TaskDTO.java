package manajero.manajerotddtasksmanagment.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TaskDTO {
    private String id;
    private String name;
    private String project;
    private String assigned;
    private String description;
    private Date dueDate;
    private String comments;
    private String status;
    private List<String> testIds;
}