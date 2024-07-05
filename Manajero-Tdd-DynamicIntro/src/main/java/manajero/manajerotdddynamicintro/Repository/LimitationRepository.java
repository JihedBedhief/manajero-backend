package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.Limitation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LimitationRepository extends MongoRepository<Limitation, String> {
}
