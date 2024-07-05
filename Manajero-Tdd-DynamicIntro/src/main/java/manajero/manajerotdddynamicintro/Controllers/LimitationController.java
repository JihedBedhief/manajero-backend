package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Entity.Limitation;
import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Services.LimitationService;
import manajero.manajerotdddynamicintro.Services.WhatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/limitation")
@CrossOrigin(origins = "http://localhost:4200")
public class LimitationController {

    @Autowired
    private LimitationService service;

    @GetMapping
    public List<Limitation> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Limitation save(@RequestBody Limitation entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
    @GetMapping("/{id}")
    public Optional<Limitation> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Limitation update(@PathVariable String id, @RequestBody Limitation newEntity) {
        return service.update(id, newEntity);
    }
}
