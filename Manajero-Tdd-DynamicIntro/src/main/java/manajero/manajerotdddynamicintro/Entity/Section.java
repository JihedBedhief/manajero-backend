package manajero.manajerotdddynamicintro.Entity;

import lombok.Getter;
import lombok.Setter;
import manajero.manajerotdddynamicintro.Dto.sectionDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "section")
public class Section {
    @Id
    private String id ;
    private String title ;
    private String description ;
    private byte[] img;

    public sectionDto getDto() {
        sectionDto sectionDto = new sectionDto();
        sectionDto.setId(id);
        sectionDto.setTitle(title);
        sectionDto.setDescription(description);
        sectionDto.setByteImg(img);

        return sectionDto;
    }
}
