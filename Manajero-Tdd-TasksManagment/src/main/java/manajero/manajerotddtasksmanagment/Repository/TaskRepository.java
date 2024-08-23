package manajero.manajerotddtasksmanagment.Repository;

import manajero.manajerotddtasksmanagment.Entities.Project;
import manajero.manajerotddtasksmanagment.Entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface TaskRepository extends MongoRepository<Task, String> {

    long countByStatus(String status);
    List<Task> findByProject_Id(String projectId);

    List<Task> findTasksByProject(Project p);

    long countByProjectId(String projectId);
    long countByAssigned(String assigned);
    @Query(value = "{}", fields = "{ 'assigned' : 1 }")
    List<Task> findAllWithAssignedField();

    default List<String> findDistinctAssigned() {
        // Retrieve all tasks with the 'assigned' field
        List<Task> tasks = findAllWithAssignedField();

        // Extract distinct 'assigned' values
        return tasks.stream()
                .map(Task::getAssigned)
                .distinct()
                .collect(Collectors.toList());
    }

    List<Task> findTasksByProjectId(String projectId);
}
