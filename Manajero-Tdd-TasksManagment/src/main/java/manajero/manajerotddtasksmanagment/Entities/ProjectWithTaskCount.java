package manajero.manajerotddtasksmanagment.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectWithTaskCount {
    private String name;
    private long taskCount;
}