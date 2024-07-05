package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.Why;
import manajero.manajerotdddynamicintro.Repository.WhyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
}
