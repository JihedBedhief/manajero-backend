package manajero.manajerotddtasksmanagment.Services;


import manajero.manajerotddtasksmanagment.Entities.Project;
import manajero.manajerotddtasksmanagment.Entities.ProjectDto;
import manajero.manajerotddtasksmanagment.Repository.ProjectRepository;
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

    public Project save(ProjectDto entity) {

        Project p = new Project();
        p.setName(entity.getName());
        p.setDescription(entity.getDescription());
        p.setStartDate(entity.getStartDate());
        p.setEndDate(entity.getEndDate());

        return repository.save(p);
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
