package manajero.manajerotddtasksmanagment.Services;

import lombok.extern.slf4j.Slf4j;
import manajero.manajerotddtasksmanagment.Controllers.TaskController;
import manajero.manajerotddtasksmanagment.Entities.*;
import manajero.manajerotddtasksmanagment.Repository.ProjectRepository;
import manajero.manajerotddtasksmanagment.Repository.TaskRepository;
import manajero.manajerotddtasksmanagment.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ArchiveService archiveService;

    public static class DistinctAssignedResult {
        private String assigned;

        // getters and setters

        public String getAssigned() {
            return assigned;
        }

        public void setAssigned(String assigned) {
            this.assigned = assigned;
        }
    }

    public List<String> getDistinctAssignedValues() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("assigned") // Group by the 'assigned' field
                        .count().as("count"), // You can include a count if needed
                Aggregation.project("assigned") // Project the 'assigned' field
                        .andExclude("_id") // Exclude the '_id' field if you want
        );

        AggregationResults<DistinctAssignedResult> results = mongoTemplate.aggregate(aggregation, "task", DistinctAssignedResult.class);
        return results.getMappedResults().stream()
                .map(DistinctAssignedResult::getAssigned)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Task> findTasksWithoutProject() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("id", "name", "assigned", "description", "dueDate", "status", "comments", "tests")
        );

        AggregationResults<Task> results = mongoTemplate.aggregate(aggregation, "task", Task.class);
        return results.getMappedResults();
    }

    public List<Task> getAllItems() {
        return taskRepository.findAll();
    }

    public Task save(TaskDTO taskDTO,List<String>l) {

        log.info("dto"+taskDTO);
        log.info("dto"+l);


        Task task = new Task();
        task.setName(taskDTO.getName());
        Project p = projectRepository.findById(taskDTO.getProject()).get();
        task.setProject(p);
        task.setAssigned(taskDTO.getAssigned());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setComments(taskDTO.getComments());
        task.setStatus("To do");

        List<Tests> testList = new ArrayList<>();
        if (l != null) {
            for (String testId : l) {
                testRepository.findById(testId).ifPresent(testList::add);
            }
        }
        task.setTests(testList);
        archiveService.archiveEntities();

        return taskRepository.save(task);
    }
    public Task update(String id, TaskDTO taskDTO) {
        Optional<Task> existingEntityOptional = taskRepository.findById(id);
        if (existingEntityOptional.isPresent()) {
            Task existingTask = existingEntityOptional.get();
            existingTask.setName(taskDTO.getName());
           // existingTask.setProject(taskDTO.getProject());
            existingTask.setAssigned(taskDTO.getAssigned());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setDueDate(taskDTO.getDueDate());
            existingTask.setComments(taskDTO.getComments());

            // Convert test IDs to Tests objects
            List<Tests> associatedTests = testRepository.findAllById(taskDTO.getTestIds());
            existingTask.setTests(associatedTests);

            return taskRepository.save(existingTask);
        } else {
            throw new IllegalArgumentException("Task entity with id " + id + " not found.");
        }
    }

    public long t(String projectId) {

        List<Task> l = taskRepository.findAll();
        log.info("list"+l);
        log.info("list"+projectId);

        long t = 0L;
        for (Task i:l
             ) {
            if (i.getProject().getId().equals(projectId)){
                t=t+1;
            }

        }
        return t;
      //  return taskRepository.findByProject_Id(projectId).size();
    }


    public long getTaskCountByProjectId(String projectId) {
        return taskRepository.countByProjectId(projectId);
    }

    public long getTaskCountByassigned(String assigned) {
        return taskRepository.countByAssigned(assigned);
    }


    public List<ColaboratorWithTaskCount> getAssignedsWithTaskCounts() {
        // Initialize a list to hold collaborators
        List<String> colab = taskRepository.findDistinctAssigned();

        // Fetch all tasks from the repository

        // Process the collaborators to count tasks and create DTOs
        return colab.stream()
                .distinct() // Ensure that each collaborator is processed only once
                .map(col -> {
                    long taskCount = getTaskCountByassigned(col); // Ensure method name matches and is correct
                    log.info("Task count for collaborator: " + taskCount +col);

                    ColaboratorWithTaskCount dto = new ColaboratorWithTaskCount();
                    dto.setAssigned(col);
                    dto.setTaskCount(taskCount);
                    return dto;
                })
                .collect(Collectors.toList());
    }



    public void deleteItem(String id) {
        taskRepository.deleteById(id);
    }

    public Task getTaskById(String id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()){
            return optionalTask.get();
        }else {
            return null;
        }

    }
}
