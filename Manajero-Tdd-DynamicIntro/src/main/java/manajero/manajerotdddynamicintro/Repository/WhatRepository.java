package manajero.manajerotdddynamicintro.Repository;

import manajero.manajerotdddynamicintro.Entity.What;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WhatRepository extends MongoRepository<What, String> {
}
