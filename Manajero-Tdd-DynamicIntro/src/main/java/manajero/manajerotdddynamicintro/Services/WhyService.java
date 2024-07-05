package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.Why;
import manajero.manajerotdddynamicintro.Repository.WhyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class WhyService {

    @Autowired
    private WhyRepository repository;

    public List<Why> getAll() {
        return repository.findAll();
    }

    public Why save(Why entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<Why> getById(String id) {
        return repository.findById(id);
    }

    public Why update(String id, Why newEntity) {
        Optional<Why> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            Why existingEntity = existingEntityOptional.get();
            existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("Why entity with id " + id + " not found.");
        }
    }
}
