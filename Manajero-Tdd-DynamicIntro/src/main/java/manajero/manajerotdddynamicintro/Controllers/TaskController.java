package manajero.manajerotdddynamicintro.Controllers;

import manajero.manajerotdddynamicintro.Entity.Task;
import manajero.manajerotdddynamicintro.Services.AccordionService;
import manajero.manajerotdddynamicintro.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public List<Task> getAllItems() {
        return taskService.getAllItems();
    }

    @PostMapping
    public void addItem(@RequestBody Task item) {
        taskService.addItem(item);
    }

    @PutMapping("/{id}")
    public Task updateItem(@PathVariable String id, @RequestBody Task updatedItem) {
        return taskService.updateItem(id, updatedItem);
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
