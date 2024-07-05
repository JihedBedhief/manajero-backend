package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Entity.How;
import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Services.HowService;
import manajero.manajerotdddynamicintro.Services.WhatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/how")
@CrossOrigin(origins = "http://localhost:4200")
public class HowController {
    @Autowired
    private HowService service;

    @GetMapping
    public List<How> getAll() {
        return service.getAll();
    }

    @PostMapping
    public How save(@RequestBody How entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
    @GetMapping("/{id}")
    public Optional<How> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public How update(@PathVariable String id, @RequestBody How newEntity) {
        return service.update(id, newEntity);
    }
}
