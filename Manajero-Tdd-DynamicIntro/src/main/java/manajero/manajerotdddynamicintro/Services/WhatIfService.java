package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.What;
import manajero.manajerotdddynamicintro.Entity.WhatIf;
import manajero.manajerotdddynamicintro.Entity.Why;
import manajero.manajerotdddynamicintro.Repository.WhatIfRepository;
import manajero.manajerotdddynamicintro.Repository.WhatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WhatIfService {

    @Autowired
    private WhatIfRepository repository;

    public List<WhatIf> getAll() {
        return repository.findAll();
    }

    public WhatIf save(WhatIf entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }


    public Optional<WhatIf> getById(String id) {
        return repository.findById(id);
    }

    public WhatIf update(String id, WhatIf newEntity) {
        Optional<WhatIf> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            WhatIf existingEntity = existingEntityOptional.get();
            existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("WhatIf entity with id " + id + " not found.");
        }
    }
}
