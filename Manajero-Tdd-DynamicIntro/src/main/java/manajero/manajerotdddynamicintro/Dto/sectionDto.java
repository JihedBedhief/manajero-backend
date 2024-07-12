package manajero.manajerotdddynamicintro.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class sectionDto {
    private String id ;
    private String title ;
    private String description ;
    private byte[] byteImg;
    private MultipartFile img;

}
