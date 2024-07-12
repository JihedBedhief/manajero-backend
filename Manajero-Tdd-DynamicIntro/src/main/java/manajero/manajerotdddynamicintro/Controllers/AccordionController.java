package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Dto.sectionDto;
import manajero.manajerotdddynamicintro.Entity.Accordion;
import manajero.manajerotdddynamicintro.Services.AccordionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accordion")
@CrossOrigin(origins = "http://localhost:4200")
public class AccordionController {
    @Autowired
    AccordionService accordionService;

    @GetMapping
    public List<Accordion> getAllItems() {
        return accordionService.getAllItems();
    }

    @PostMapping
    public void addItem(@RequestBody Accordion item) {
        accordionService.addItem(item);
    }

    @PutMapping("/{id}")
    public Accordion updateItem(@PathVariable String id, @RequestBody Accordion updatedItem) {
        return accordionService.updateItem(id, updatedItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        accordionService.deleteItem(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accordion> getAccordionById(@PathVariable String id){
        Accordion accordion = accordionService.getAccordionById(id);
        if (accordion != null){
            return ResponseEntity.ok(accordion);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
