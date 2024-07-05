package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.How;
import manajero.manajerotdddynamicintro.Entity.Intro;
import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Repository.HowRepository;
import manajero.manajerotdddynamicintro.Repository.WhatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HowService {
    @Autowired
    private HowRepository repository;

    public List<How> getAll() {
        return repository.findAll();
    }

    public How save(How entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<How> getById(String id) {
        return repository.findById(id);
    }

    public How update(String id, How newEntity) {
        Optional<How> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            How existingEntity = existingEntityOptional.get();
            existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("How entity with id " + id + " not found.");
        }
    }
}
