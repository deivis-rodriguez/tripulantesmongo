package edu.mintic.tripulantesmongo.config.userdetails;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.mintic.tripulantesmongo.entity.Usuario;

public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, String email,
            Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario usuario) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRole().getNombre()));
        return new UserDetailsImpl(usuario.getUsername(), usuario.getPassword(), usuario.getEmail(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return false;
    }

    @Override
    public boolean isAccountNonLocked() {

        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return false;
    }

    @Override
    public boolean isEnabled() {

        return false;
    }

    public String getEmail() {
        return email;
    }

}
