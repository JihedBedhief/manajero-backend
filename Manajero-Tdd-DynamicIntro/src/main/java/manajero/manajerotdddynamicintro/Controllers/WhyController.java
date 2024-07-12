package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Entity.Why;
import manajero.manajerotdddynamicintro.Services.WhyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/why")
@CrossOrigin(origins = "http://localhost:4200")
public class WhyController {

    @Autowired
    private WhyService service;

    @GetMapping
    public List<Why> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Why save(@RequestBody Why entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }


    @GetMapping("/{id}")
    public Optional<Why> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Why update(@PathVariable String id, @RequestBody Why newEntity) {
        return service.update(id, newEntity);
    }
}
