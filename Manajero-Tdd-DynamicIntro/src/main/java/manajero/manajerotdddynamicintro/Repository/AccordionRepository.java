package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.Accordion;
import manajero.manajerotdddynamicintro.Entity.Avantage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccordionRepository extends MongoRepository<Accordion, String> {
}
