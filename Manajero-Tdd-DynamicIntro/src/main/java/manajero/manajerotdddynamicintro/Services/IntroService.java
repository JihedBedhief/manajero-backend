package manajero.manajerotdddynamicintro.Services;


import manajero.manajerotdddynamicintro.Entity.How;
import manajero.manajerotdddynamicintro.Entity.Intro;
import manajero.manajerotdddynamicintro.Entity.Limitation;
import manajero.manajerotdddynamicintro.Repository.HowRepository;
import manajero.manajerotdddynamicintro.Repository.IntroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntroService {

    @Autowired
    private IntroRepository repository;

    public List<Intro> getAll() {
        return repository.findAll();
    }

    public Intro save(Intro entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<Intro> getById(String id) {
        return repository.findById(id);
    }

    public Intro update(String id, Intro newEntity) {
        Optional<Intro> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            Intro existingEntity = existingEntityOptional.get();
            existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("Intro entity with id " + id + " not found.");
        }
    }
}
