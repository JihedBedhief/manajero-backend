package manajero.manajerotddtasksmanagment.Services;

import lombok.extern.slf4j.Slf4j;

import manajero.manajerotddtasksmanagment.Entities.Tests;
import manajero.manajerotddtasksmanagment.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TestService {

    @Autowired
    private TestRepository repository;

    public List<Tests> getAll() {
        return repository.findAll();
    }

    public Tests save(Tests entity) {
        entity.setStatus(false);

        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<Tests> getById(String id) {
        return repository.findById(id);
    }

    public Tests update(String id, Tests newEntity) {
        Optional<Tests> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            Tests existingEntity = existingEntityOptional.get();
            existingEntity.setTitle(newEntity.getTitle());
            existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("What entity with id " + id + " not found.");
        }
    }
}
