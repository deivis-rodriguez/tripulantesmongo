package edu.mintic.tripulantesmongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.mintic.tripulantesmongo.config.jwt.AuthenticationEntryPointImpl;
import edu.mintic.tripulantesmongo.config.jwt.FiltroToken;
import edu.mintic.tripulantesmongo.config.userDetails.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPointImpl;

    @Bean
    public FiltroToken filtroToken() {
        return new FiltroToken();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().
        exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();
                
        http.addFilterBefore(filtroToken(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public UserDetailsServiceImpl detailsServiceImpl(){
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsServiceImpl()).passwordEncoder(passwordEncoder());
        /*
         * auth.inMemoryAuthentication()
         * .withUser("deivis").password("{noop}password").roles("USER")
         * .and()
         * .withUser("maria").password("{noop}password").roles("ADMIN");
         */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

}
