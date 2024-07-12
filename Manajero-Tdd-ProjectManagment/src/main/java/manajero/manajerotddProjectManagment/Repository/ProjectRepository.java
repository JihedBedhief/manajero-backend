package manajero.manajerotddProjectManagment.Repository;


import manajero.manajerotddProjectManagment.Entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
