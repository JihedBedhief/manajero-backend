package manajero.manajerotddtasksmanagment.Services;


import lombok.extern.slf4j.Slf4j;
import manajero.manajerotddtasksmanagment.Entities.Project;
import manajero.manajerotddtasksmanagment.Entities.ProjectDto;
import manajero.manajerotddtasksmanagment.Entities.ProjectWithTaskCount;
import manajero.manajerotddtasksmanagment.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProjectService {


    @Autowired
    private ProjectRepository repository;

    @Autowired
    private TaskService taskService;
    @Autowired
    private ArchiveService archiveService;


    public List<ProjectWithTaskCount> getProjectsWithTaskCounts() {
        List<Project> projects = repository.findAll();
        return projects.stream().map(project -> {
            long taskCount = taskService.getTaskCountByProjectId(project.getId());
            log.info("list"+taskCount);

            ProjectWithTaskCount dto = new ProjectWithTaskCount();
            dto.setName(project.getName());
            dto.setTaskCount(taskCount);
            return dto;
        }).collect(Collectors.toList());
    }

  /*  @Scheduled(fixedRate = 10000)
    public void tt() {
        List<Project> projects = repository.findAll();
        List<ProjectWithTaskCount> projectWithTaskCounts = projects.stream().map(project -> {
            long taskCount = taskService.getTaskCountByProjectId(project.getId());
            log.info("Project: {} has {} tasks", project.getName(), taskCount);

            ProjectWithTaskCount dto = new ProjectWithTaskCount();
            dto.setName(project.getName());
            dto.setTaskCount(taskCount);
            return dto;
        }).collect(Collectors.toList());

        // Optionally log or process the list of ProjectWithTaskCount DTOs
        log.info("Project task counts: {}", projectWithTaskCounts);
    }*/


    public List<Project> getAll() {
        return repository.findAll();
    }

    public Project save(ProjectDto entity) {

        Project p = new Project();
        p.setName(entity.getName());
        p.setDescription(entity.getDescription());
        p.setStartDate(entity.getStartDate());
        p.setEndDate(entity.getEndDate());

       archiveService.updateprojArchive(p);

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
