package edu.mintic.tripulantesmongo.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import edu.mintic.tripulantesmongo.config.userdetails.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private long duracion = 60 * 60 * 1000;
    private String secrect = "textoclave";

    public String generarToken(UserDetailsImpl userDetailsImpl) {
        Date date = new Date();
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetailsImpl.getAuthorities());
        return Jwts.builder().setSubject(userDetailsImpl.getUsername())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + duracion))
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secrect)
                .compact();
    }

    public boolean validarToken(String token){
        try {
            Jwts.parser().setSigningKey(secrect).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getUsername(String token){
        return Jwts.parser().setSigningKey(secrect).parseClaimsJws(token).getBody().getSubject();
    }

}
