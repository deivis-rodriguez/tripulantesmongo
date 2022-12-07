package edu.mintic.tripulantesmongo.config.jwt;

import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import edu.mintic.tripulantesmongo.config.userDetails.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
    private String jwtSecrect = Base64.getEncoder().encodeToString("tripulante".getBytes());
    private long fechaExp = 30 * 60 * 1000;

    public String generarToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + fechaExp))
                .claim("roles", userDetails.getAuthorities())
                .signWith(SignatureAlgorithm.HS256, jwtSecrect)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecrect).parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            LOGGER.error("el token no es valido: {}", e.getMessage());
        }
        return false;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(jwtSecrect).parseClaimsJws(token).getBody().getSubject();
    }
}
