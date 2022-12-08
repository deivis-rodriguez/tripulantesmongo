package edu.mintic.tripulantesmongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.mintic.tripulantesmongo.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
