package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Services.WhatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/what")
@CrossOrigin(origins = "http://localhost:4200")
public class WhatController {

    @Autowired
    private WhatService service;

    @GetMapping
    public List<What> getAll() {
        return service.getAll();
    }

    @PostMapping
    public What save(@RequestBody What entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
    @GetMapping("/{id}")
    public Optional<What> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public What update(@PathVariable String id, @RequestBody What newEntity) {
        return service.update(id, newEntity);
    }
}