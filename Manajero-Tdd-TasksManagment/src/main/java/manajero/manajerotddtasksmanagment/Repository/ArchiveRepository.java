package manajero.manajerotddtasksmanagment.Repository;

import manajero.manajerotddtasksmanagment.Entities.Archive;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArchiveRepository extends MongoRepository<Archive, String> {
}
