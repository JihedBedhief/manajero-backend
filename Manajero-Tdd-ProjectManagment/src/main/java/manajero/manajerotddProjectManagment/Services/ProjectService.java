package manajero.manajerotddProjectManagment.Services;


import manajero.manajerotddProjectManagment.Entities.Project;
import manajero.manajerotddProjectManagment.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    @Autowired
    private ProjectRepository repository;

    public List<Project> getAll() {
        return repository.findAll();
    }

    public Project save(Project entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<Project> getById(String id) {
        return repository.findById(id);
    }

    public Project update(String id, Project newEntity) {
        Optional<Project> existingEntityOptional = repository.findById(id);
        if (existingEntityOptional.isPresent()) {
            Project existingEntity = existingEntityOptional.get();
           // existingEntity.setDescription(newEntity.getDescription());
            return repository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("Why entity with id " + id + " not found.");
        }
    }
}
