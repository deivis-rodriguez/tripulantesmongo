package edu.mintic.tripulantesmongo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.mintic.tripulantesmongo.config.userdetails.UserDetailsServiceImpl;
import edu.mintic.tripulantesmongo.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FiltroToken extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl detailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String headerToken = request.getHeader("Authorization");
            String token = null;
            if (StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer ")) {
                token = headerToken.substring(7);
            }

            if (token != null && jwtUtils.validarToken(token)) {
                String username = jwtUtils.getUsername(token);
                UserDetails userDetails = detailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(request);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        } catch (Exception e) {
        }

        filterChain.doFilter(request, response);
    }

}
