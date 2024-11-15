package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Entity.Why;
import manajero.manajerotdddynamicintro.Repository.WhatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WhatService {

    @Autowired
    private WhatRepository repository;

    public List<What> getAll() {
        return repository.findAll();
    }

    public What save(What entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<What> getById(String id) {
        return repository.findById(id);
    }

    public What update(String id, What newEntity) {
        Optional<What> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            What existingEntity = existingEntityOptional.get();
            existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("What entity with id " + id + " not found.");
        }
    }
}