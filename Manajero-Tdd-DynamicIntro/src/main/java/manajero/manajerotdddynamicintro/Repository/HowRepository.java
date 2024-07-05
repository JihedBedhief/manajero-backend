package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.How;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HowRepository extends MongoRepository<How, String> {
}