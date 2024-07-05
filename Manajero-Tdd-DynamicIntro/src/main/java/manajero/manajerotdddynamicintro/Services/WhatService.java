package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Repository.WhatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
}