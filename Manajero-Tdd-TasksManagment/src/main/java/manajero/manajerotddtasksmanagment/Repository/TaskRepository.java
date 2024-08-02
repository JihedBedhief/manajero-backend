package manajero.manajerotddtasksmanagment.Repository;

import manajero.manajerotddtasksmanagment.Entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
