package manajero.manajerotddtasksmanagment.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ProjectDto {

    private String id ;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
}
