package manajero.manajerotddtasksmanagment.Controllers;
import com.fasterxml.jackson.core.util.RequestPayload;
import manajero.manajerotddtasksmanagment.Entities.ColaboratorWithTaskCount;
import manajero.manajerotddtasksmanagment.Entities.ProjectWithTaskCount;
import manajero.manajerotddtasksmanagment.Entities.Task;
import manajero.manajerotddtasksmanagment.Entities.TaskDTO;
import manajero.manajerotddtasksmanagment.Repository.TaskRepository;
import manajero.manajerotddtasksmanagment.Repository.TestRepository;
import manajero.manajerotddtasksmanagment.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    TaskService taskService;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TestRepository testRepository;

    @GetMapping
    public List<Task> getAllItems() {
        return taskService.getAllItems();
    }

    @PostMapping("/{l}")
    public Task createTask(@RequestBody  TaskDTO taskDTO, @PathVariable List<String> l) {
        return taskService.save(taskDTO,l);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return taskService.update(id, taskDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        taskService.deleteItem(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id){
        Task task = taskService.getTaskById(id);
        if (task != null){
            return ResponseEntity.ok(task);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/Kpis")
    public ResponseEntity<Map<String, Object>> getTaskKPIs() {
        Map<String, Object> kpis = new HashMap<>();

        long totalTasks = taskRepository.count();
        long toDoTasks = taskRepository.countByStatus("To do");
        long inProgressTasks = taskRepository.countByStatus("In Progress");
        long doneTasks = taskRepository.countByStatus("Done");
        double completionRate = (totalTasks > 0) ? (doneTasks / (double) totalTasks) * 100 : 0;

        kpis.put("totalTasks", totalTasks);
        kpis.put("toDoTasks", toDoTasks);
        kpis.put("inProgressTasks", inProgressTasks);
        kpis.put("doneTasks", doneTasks);
        kpis.put("completionRate", completionRate);

        return ResponseEntity.ok(kpis);
    }
    @GetMapping("/AllKpis")
    public ResponseEntity<Map<String, Object>> getAllKPIs() {
        Map<String, Object> kpis = new HashMap<>();

        long totalTasks = taskRepository.count();
        long totalTests = testRepository.count();
        long toDoTasks = taskRepository.countByStatus("To do");
        long inProgressTasks = taskRepository.countByStatus("In Progress");
        long doneTasks = taskRepository.countByStatus("Done");
        double completionRate = (totalTasks > 0) ? (doneTasks / (double) totalTasks) * 100 : 0;
        long passedTests = testRepository.countByStatus(true);
        long failedTests = testRepository.countByStatus(false);
        double testPassRate = (totalTests > 0) ? (passedTests / (double) totalTests) * 100 : 0;

        kpis.put("totalTasks", totalTasks);
        kpis.put("totalTests", totalTests);
        kpis.put("completionRate", completionRate);
        kpis.put("completionRatetest", testPassRate);


        return ResponseEntity.ok(kpis);
    }

    @GetMapping("/with-task-counts")
    public List<ColaboratorWithTaskCount> getAssignedsWithTaskCounts() {
        return taskService.getAssignedsWithTaskCounts();
    }


    @GetMapping("/tasks/{project_id}")
    public  List<Task> getTasksByProject(@PathVariable("project_id") String project_id){
        return taskService.TasksByProject(project_id);
    }

    @PatchMapping("/task/status/{id}")
    public Task updateStatus(@RequestBody String status, @PathVariable String id){
        return taskService.updateTaskStatus(status,id);
    }
}
