package manajero.manajerotdddynamicintro.Services;

import manajero.manajerotdddynamicintro.Entity.Task;
import manajero.manajerotdddynamicintro.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAllItems() {
        return taskRepository.findAll();
    }

    public void addItem(Task item) {
        taskRepository.save(item);
    }

    public Task updateItem(String id, Task updatedItem) {
        return taskRepository.findById(id)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setProject(updatedItem.getProject());
                    item.setAssigned(updatedItem.getAssigned());
                    item.setDescription(updatedItem.getDescription());
                    item.setDueDate(updatedItem.getDueDate());
                    item.setStatus(updatedItem.getStatus());
                    item.setComments(updatedItem.getComments());
                    return taskRepository.save(item);
                }).orElseThrow(() -> new NullPointerException("Item not found"));
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
