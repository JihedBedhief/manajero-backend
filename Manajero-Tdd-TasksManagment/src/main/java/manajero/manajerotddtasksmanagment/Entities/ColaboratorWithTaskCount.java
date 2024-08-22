package manajero.manajerotddtasksmanagment.Entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboratorWithTaskCount {

    private String assigned;
    private long taskCount;
}