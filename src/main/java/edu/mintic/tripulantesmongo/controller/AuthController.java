package edu.mintic.tripulantesmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mintic.tripulantesmongo.config.userdetails.UserDetailsImpl;
import edu.mintic.tripulantesmongo.config.userdetails.UserDetailsServiceImpl;
import edu.mintic.tripulantesmongo.entity.Role;
import edu.mintic.tripulantesmongo.entity.Usuario;
import edu.mintic.tripulantesmongo.payloads.JwtResponse;
import edu.mintic.tripulantesmongo.payloads.MsgResponse;
import edu.mintic.tripulantesmongo.payloads.SigninRequest;
import edu.mintic.tripulantesmongo.payloads.SignupRequest;
import edu.mintic.tripulantesmongo.repository.RoleRepository;
import edu.mintic.tripulantesmongo.repository.UsuarioRepository;
import edu.mintic.tripulantesmongo.utils.JwtUtils;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserDetailsServiceImpl detailsServiceImpl;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody SigninRequest request) {

        UserDetailsImpl userDetails = (UserDetailsImpl) detailsServiceImpl.loadUserByUsername(request.getUsername());

        if (!encoder.matches(request.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.badRequest().body(new MsgResponse("Contraseña invalida"));
        }

        String token = jwtUtils.generarToken(userDetails);

        return ResponseEntity.ok().body(new JwtResponse(token));
    }

    @PostMapping("/auth/registro")
    public ResponseEntity<?> registrarse(@RequestBody SignupRequest request) {

        if (usuarioRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new MsgResponse("el nombre de usuario ya existe"));
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MsgResponse("el correo ya esta en uso"));
        }

        Role role = roleRepository.findByNombre(request.getRole()).orElse(null);

        if (role == null) {
            return ResponseEntity.badRequest().body(new MsgResponse("el role no es correcto"));
        }

        Usuario usuario = new Usuario(request.getUsername(), encoder.encode(request.getPassword()), request.getEmail(),
                role);

        return ResponseEntity.ok().body(usuarioRepository.save(usuario));
    }

}
