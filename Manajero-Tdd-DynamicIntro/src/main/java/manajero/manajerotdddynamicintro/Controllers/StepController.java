package manajero.manajerotdddynamicintro.Controllers;


import manajero.manajerotdddynamicintro.Entity.Limitation;
import manajero.manajerotdddynamicintro.Entity.Step;
import manajero.manajerotdddynamicintro.Services.SectionService;
import manajero.manajerotdddynamicintro.Services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/step")
@CrossOrigin(origins = "http://localhost:4200")
public class StepController {
    @Autowired
    StepService stepService;

    @GetMapping
    public List<Step> getAll() {
        return stepService.getAll();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Step> addStep(
            @RequestPart("label") String label,
            @RequestPart("title") String title,
            @RequestPart("description") String description) {

        Step step = new Step();
        step.setLabel(label);
        step.setTitle(title);
        step.setDescription(description);
        stepService.save(step);
        return ResponseEntity.ok(step);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        stepService.delete(id);
    }
}
