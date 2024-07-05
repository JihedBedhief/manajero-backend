package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.Avantage;
import manajero.manajerotdddynamicintro.Entity.How;
import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Repository.AvantageReository;
import manajero.manajerotdddynamicintro.Repository.WhatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AvantageService {
    @Autowired
    private AvantageReository repository;

    public List<Avantage> getAll() {
        return repository.findAll();
    }

    public Avantage save(Avantage entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }


    public Optional<Avantage> getById(String id) {
        return repository.findById(id);
    }

    public Avantage update(String id, Avantage newEntity) {
        Optional<Avantage> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            Avantage existingEntity = existingEntityOptional.get();
            existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("Avantage entity with id " + id + " not found.");
        }
    }
}
