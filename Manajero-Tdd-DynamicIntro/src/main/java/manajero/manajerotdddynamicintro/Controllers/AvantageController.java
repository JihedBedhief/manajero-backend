package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Entity.Avantage;
import manajero.manajerotdddynamicintro.Entity.Limitation;
import manajero.manajerotdddynamicintro.Services.AvantageService;
import manajero.manajerotdddynamicintro.Services.LimitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avantage")
@CrossOrigin(origins = "http://localhost:4200")
public class AvantageController {

    @Autowired
    private AvantageService service;

    @GetMapping
    public List<Avantage> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Avantage save(@RequestBody Avantage entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Avantage> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Avantage update(@PathVariable String id, @RequestBody Avantage newEntity) {
        return service.update(id, newEntity);
    }
}
