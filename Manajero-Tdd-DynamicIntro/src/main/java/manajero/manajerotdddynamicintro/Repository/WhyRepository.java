package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.Why;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WhyRepository extends MongoRepository<Why, String> {
}
