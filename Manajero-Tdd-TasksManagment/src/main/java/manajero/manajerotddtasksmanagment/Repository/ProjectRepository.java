package manajero.manajerotddtasksmanagment.Repository;


import manajero.manajerotddtasksmanagment.Entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
