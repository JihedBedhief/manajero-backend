package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.Limitation;
import manajero.manajerotdddynamicintro.Entity.Section;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SectionRepository extends MongoRepository<Section, String> {
}
