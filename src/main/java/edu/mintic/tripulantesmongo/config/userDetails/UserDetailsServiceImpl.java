package edu.mintic.tripulantesmongo.config.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.mintic.tripulantesmongo.entity.Usuario;
import edu.mintic.tripulantesmongo.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("el usuario no existe");
        });

        return UserDetailsImpl.build(usuario);
    }

}
