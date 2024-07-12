package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.Section;
import manajero.manajerotdddynamicintro.Entity.Step;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StepRepositoy extends MongoRepository<Step, String> {
}
