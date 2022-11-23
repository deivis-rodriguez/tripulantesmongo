package edu.mintic.tripulantesmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.mintic.tripulantesmongo.entity.Tripulante;

public interface TripulanteRepository extends MongoRepository<Tripulante, String> {
    
}
