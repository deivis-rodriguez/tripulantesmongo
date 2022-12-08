package edu.mintic.tripulantesmongo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mintic.tripulantesmongo.config.jwt.JwtUtils;
import edu.mintic.tripulantesmongo.config.userDetails.UserDetailsImpl;
import edu.mintic.tripulantesmongo.config.userDetails.UserDetailsServiceImpl;
import edu.mintic.tripulantesmongo.entity.Role;
import edu.mintic.tripulantesmongo.entity.Usuario;
import edu.mintic.tripulantesmongo.payloads.JwtResponse;
import edu.mintic.tripulantesmongo.payloads.MsgResponse;
import edu.mintic.tripulantesmongo.payloads.SigninRequest;
import edu.mintic.tripulantesmongo.payloads.SignupRequest;
import edu.mintic.tripulantesmongo.repository.RoleRepository;
import edu.mintic.tripulantesmongo.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    private UsuarioRepository usuarioRepository;
    private RoleRepository roleRepository;
    private JwtUtils jwtUtils;
    private PasswordEncoder enconder;
    private UserDetailsServiceImpl detailsServiceImpl;

    public AuthController(UsuarioRepository usuarioRepository, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder enconder,
            UserDetailsServiceImpl detailsServiceImpl) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.enconder = enconder;
        this.detailsServiceImpl = detailsServiceImpl;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registrarUsuario(@RequestBody SignupRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new MsgResponse("error, el nombre de usuario existe"));
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MsgResponse("error, el email de usuario existe"));
        }

        Role role = roleRepository.findByNombre(request.getRole()).orElseThrow();

        Usuario usuario = new Usuario(request.getUsername(), enconder.encode(request.getPassword()), request.getEmail(),
                role);
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> inicioSesion(@RequestBody SigninRequest request) {
        UserDetailsImpl userDetails = (UserDetailsImpl) detailsServiceImpl.loadUserByUsername(request.getUsername());
        if (!enconder.matches(request.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.badRequest().body(new MsgResponse("la contraseña no es correcta"));
        }
        String token = jwtUtils.generarToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

}
