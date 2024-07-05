package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.WhatIf;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WhatIfRepository extends MongoRepository<WhatIf, String> {
}