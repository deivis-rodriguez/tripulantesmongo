package edu.mintic.tripulantesmongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();
        http.authorizeRequests()
        //.antMatchers("/tripulantes").hasRole("ADMIN")
        .anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("deivis").password("{noop}password").roles("USER")
        .and()
        .withUser("maria").password("{noop}password").roles("ADMIN");
	}


}
