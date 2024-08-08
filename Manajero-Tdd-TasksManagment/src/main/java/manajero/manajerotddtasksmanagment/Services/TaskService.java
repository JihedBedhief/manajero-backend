package manajero.manajerotddtasksmanagment.Services;

import lombok.extern.slf4j.Slf4j;
import manajero.manajerotddtasksmanagment.Controllers.TaskController;
import manajero.manajerotddtasksmanagment.Entities.Task;
import manajero.manajerotddtasksmanagment.Entities.TaskDTO;
import manajero.manajerotddtasksmanagment.Entities.Tests;
import manajero.manajerotddtasksmanagment.Repository.TaskRepository;
import manajero.manajerotddtasksmanagment.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TestRepository testRepository;

    public List<Task> getAllItems() {
        return taskRepository.findAll();
    }

    public Task save(TaskDTO taskDTO,List<String>l) {

        log.info("dto"+taskDTO);
        log.info("dto"+l);


        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setProject(taskDTO.getProject());
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

        return taskRepository.save(task);
    }
    public Task update(String id, TaskDTO taskDTO) {
        Optional<Task> existingEntityOptional = taskRepository.findById(id);
        if (existingEntityOptional.isPresent()) {
            Task existingTask = existingEntityOptional.get();
            existingTask.setName(taskDTO.getName());
            existingTask.setProject(taskDTO.getProject());
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
