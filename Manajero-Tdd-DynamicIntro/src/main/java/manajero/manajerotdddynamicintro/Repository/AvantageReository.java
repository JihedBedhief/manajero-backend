package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.Avantage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvantageReository extends MongoRepository<Avantage, String> {
}
