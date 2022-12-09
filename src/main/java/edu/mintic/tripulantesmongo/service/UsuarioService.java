package edu.mintic.tripulantesmongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mintic.tripulantesmongo.entity.Usuario;
import edu.mintic.tripulantesmongo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Optional<Usuario> getUsuario(String username){
        return repository.findByUsername(username);
    }

}
