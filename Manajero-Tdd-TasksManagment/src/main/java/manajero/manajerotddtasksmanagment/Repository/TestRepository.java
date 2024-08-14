package manajero.manajerotddtasksmanagment.Repository;

import manajero.manajerotddtasksmanagment.Entities.Tests;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TestRepository extends MongoRepository<Tests, String> {
    Tests findTestsByTitle(String title);

    long countByStatus(boolean status);

}
