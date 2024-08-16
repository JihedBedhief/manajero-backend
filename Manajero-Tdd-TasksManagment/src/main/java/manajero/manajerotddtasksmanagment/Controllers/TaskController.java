package manajero.manajerotddtasksmanagment.Controllers;
import com.fasterxml.jackson.core.util.RequestPayload;
import manajero.manajerotddtasksmanagment.Entities.Task;
import manajero.manajerotddtasksmanagment.Entities.TaskDTO;
import manajero.manajerotddtasksmanagment.Repository.TaskRepository;
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
    public static class RequestPayload {
        private List<String> ids;
        private TaskDTO task;

        // Getters and setters
        public List<String> getIds() {
            return ids;
        }

        public void setIds(List<String> ids) {
            this.ids = ids;
        }

        public TaskDTO getTask() {
            return task;
        }

        public void setTask(TaskDTO task) {
            this.task = task;
        }
    }
    @Autowired
    TaskService taskService;
    @Autowired
    TaskRepository taskRepository;

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
}
