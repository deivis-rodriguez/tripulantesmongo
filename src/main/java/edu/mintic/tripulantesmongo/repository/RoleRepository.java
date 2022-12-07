package edu.mintic.tripulantesmongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.mintic.tripulantesmongo.entity.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByNombre(String nombre);
}
