package manajero.manajerotddtasksmanagment.Controllers;
import com.fasterxml.jackson.core.util.RequestPayload;
import manajero.manajerotddtasksmanagment.Entities.Task;
import manajero.manajerotddtasksmanagment.Entities.TaskDTO;
import manajero.manajerotddtasksmanagment.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Task> getAllItems() {
        return taskService.getAllItems();
    }

    @PostMapping()
    public Task createTask(@RequestBody RequestPayload payload) {

        List<String> ids = payload.getIds();
        TaskDTO task = payload.getTask();
        return taskService.save(payload);
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
}
