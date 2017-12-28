package com.elo7.estudo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.MapUserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsRepository;

@Configuration
public class SecurityConfiguration {

    @Bean
    UserDetailsRepository userDetailsRepository() {
        UserDetails fulano = User.withUsername("fulano").password("123").roles("USER").build();
        UserDetails denis = User.withUsername("denis").password("123").roles("USER", "ADMIN").build();
        return new MapUserDetailsRepository(fulano, denis);
    }
}
