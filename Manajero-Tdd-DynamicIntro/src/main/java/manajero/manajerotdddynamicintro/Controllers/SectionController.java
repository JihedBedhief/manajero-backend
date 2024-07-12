package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Dto.sectionDto;
import manajero.manajerotdddynamicintro.Services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/section")
@CrossOrigin(origins = "http://localhost:4200")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @PostMapping()
    public ResponseEntity<sectionDto> addSection(@ModelAttribute sectionDto sectionDto) throws IOException {
        sectionDto sectionDto1 = sectionService.addSection(sectionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sectionDto1);

    }

    @PutMapping("/{id}")
    public ResponseEntity<sectionDto> updateItem(@PathVariable String id ,@ModelAttribute sectionDto sectionDto) throws IOException {
        sectionDto sectionDto1 = sectionService.updateSection(id,sectionDto);
        if (sectionDto1 != null){
            return ResponseEntity.ok(sectionDto1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping()
    ResponseEntity<List<sectionDto>> GetAll(){
        List<sectionDto> list = sectionService.getAllSection();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);

    }
    @GetMapping("/{id}")
    public ResponseEntity<sectionDto> getSectionById(@PathVariable String id){
        sectionDto sectionDto = sectionService.getSectionById(id);
        if (sectionDto != null){
            return ResponseEntity.ok(sectionDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable String id){
        boolean delete = sectionService.deleteSection(id);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }



}
