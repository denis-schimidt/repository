/*
package com.elo7.estudo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

//@EnableWebFluxSecurity
public class SecurityConfiguration {

  //  @Bean
    MapReactiveUserDetailsService userDetailsRepository() {
        UserDetails fulano = User.withDefaultPasswordEncoder().username("fulano")
                .password("123").roles("USER").build();
        UserDetails denis = User.withDefaultPasswordEncoder().username("denis").password("123")
                .roles("USER", "ADMIN").build();
        return new MapReactiveUserDetailsService(fulano, denis);
    }
}
*/
