package com.smola.hiber.repositories;

import com.smola.hiber.model.Coordinates;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesRepository extends MongoRepository<Coordinates,String> {
}
