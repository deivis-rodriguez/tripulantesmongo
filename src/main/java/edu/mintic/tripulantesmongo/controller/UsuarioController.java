package edu.mintic.tripulantesmongo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.mintic.tripulantesmongo.entity.Usuario;
import edu.mintic.tripulantesmongo.service.UsuarioService;

@RestController
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/usuario/{username}")
    public ResponseEntity<?> getUsuario(@PathVariable String username){
        ResponseEntity<?> response;
        try {
            Usuario usuario = service.getUsuario(username).orElseThrow();
            response = ResponseEntity.ok().body(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("nombre de usuario no encontrada");
        }
        return response;
    }
    
}
