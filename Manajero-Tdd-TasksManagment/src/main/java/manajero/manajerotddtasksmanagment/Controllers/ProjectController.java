package manajero.manajerotddtasksmanagment.Controllers;



import manajero.manajerotddtasksmanagment.Entities.Project;
import manajero.manajerotddtasksmanagment.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
    @Autowired
    private ProjectService service;

    @GetMapping
    public List<Project> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Project save(@RequestBody Project entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }


    @GetMapping("/{id}")
    public Optional<Project> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable String id, @RequestBody Project newEntity) {
        return service.update(id, newEntity);
    }
}
