package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Entity.Intro;
import manajero.manajerotdddynamicintro.Services.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/intro")
@CrossOrigin(origins = "http://localhost:4200")
public class IntroController {

    @Autowired
    private IntroService service;

    @GetMapping
    public List<Intro> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Intro save(@RequestBody Intro entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Intro> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Intro update(@PathVariable String id, @RequestBody Intro newEntity) {
        return service.update(id, newEntity);
    }
}
