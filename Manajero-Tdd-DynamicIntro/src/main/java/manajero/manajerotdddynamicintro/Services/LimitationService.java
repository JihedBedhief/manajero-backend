package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.Limitation;
import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Entity.WhatIf;
import manajero.manajerotdddynamicintro.Repository.LimitationRepository;
import manajero.manajerotdddynamicintro.Repository.WhatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LimitationService {

    @Autowired
    private LimitationRepository repository;

    public List<Limitation> getAll() {
        return repository.findAll();
    }

    public Limitation save(Limitation entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<Limitation> getById(String id) {
        return repository.findById(id);
    }

    public Limitation update(String id, Limitation newEntity) {
        Optional<Limitation> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            Limitation existingEntity = existingEntityOptional.get();
            existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("Limitation entity with id " + id + " not found.");
        }
    }
}
