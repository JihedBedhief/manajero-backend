package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.Intro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IntroRepository extends MongoRepository<Intro, String> {
}
