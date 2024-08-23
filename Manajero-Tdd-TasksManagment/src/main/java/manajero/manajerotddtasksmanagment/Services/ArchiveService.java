package manajero.manajerotddtasksmanagment.Services;


import jakarta.annotation.PostConstruct;
import manajero.manajerotddtasksmanagment.Entities.Archive;
import manajero.manajerotddtasksmanagment.Entities.Project;
import manajero.manajerotddtasksmanagment.Entities.Task;
import manajero.manajerotddtasksmanagment.Entities.Tests;
import manajero.manajerotddtasksmanagment.Repository.ArchiveRepository;
import manajero.manajerotddtasksmanagment.Repository.ProjectRepository;
import manajero.manajerotddtasksmanagment.Repository.TaskRepository;
import manajero.manajerotddtasksmanagment.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArchiveService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TestRepository tstRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ArchiveRepository archiveRepository;

    public void archiveEntities() {
        // Fetch and mark projects as archived
        // Fetch and mark projects as archived
        List<Project> archivedProjects = projectRepository.findAll().stream()
                .peek(project -> {

                })
                .collect(Collectors.toList());

        // Fetch and mark TSTs as archived
        List<Tests> archivedTsts = tstRepository.findAll().stream()
                .peek(tst -> {

                })
                .collect(Collectors.toList());

        // Fetch and mark tasks as archived
        List<Task> tasks = taskRepository.findTasksWithoutProject().stream()
                .peek(task -> {

                    // Remove the project reference before archiving
                    task.setProject(null);
                })
                .collect(Collectors.toList());

        // Create and save the archive
        Archive archive = new Archive();


        // Combine projects from both sources, ensuring no duplicates
        Set<Project> combinedProjects = new HashSet<>(archivedProjects);

        archive.setProjects(new ArrayList<>(combinedProjects));
        archive.setTsts(archivedTsts);
        archive.setTasks(tasks);

        archiveRepository.deleteAll();

        archiveRepository.save(archive);


    }

    public void updateprojArchive(Project p ){
        List<Archive> l =archiveRepository.findAll();
        for (Archive i:l
             ) {
            List<Project> projects =i.getProjects();
            projects.add(p);
            archiveRepository.save(i);
        }
    }
    public void updatetaskArchive(Task p ){
        List<Archive> l =archiveRepository.findAll();
        for (Archive i:l
        ) {
            List<Task> projects =i.getTasks();
            projects.add(p);
            archiveRepository.save(i);
        }
    }
    public void updatetestArchive(Tests p ){
        List<Archive> l =archiveRepository.findAll();
        for (Archive i:l
        ) {
            List<Tests> projects =i.getTsts();
            projects.add(p);
            archiveRepository.save(i);
        }
    }
}
