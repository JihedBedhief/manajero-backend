package manajero.manajerotddtasksmanagment.Repository;

import manajero.manajerotddtasksmanagment.Entities.Tests;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Tests, String> {
}
