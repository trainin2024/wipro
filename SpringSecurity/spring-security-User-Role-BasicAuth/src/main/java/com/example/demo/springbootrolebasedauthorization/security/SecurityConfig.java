package com.example.demo.springbootrolebasedauthorization.security;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.HttpBasicDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests((authorize) ->
                authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                         .requestMatchers("/api/auth/**").permitAll()
                         .requestMatchers("/api/admin/**").hasRole("ADMIN")
                         .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
}


  /*protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		.requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ROLE_ADMIN")
                .requestMatchers("/api/user/**").hasRole("Role_USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();            
  }*/

/*
 * In Spring Security 5.6, we can enable annotation-based security using the @EnableMethodSecurity 
 * annotation on any @Configuration instance. @EnableMethodSecurity enables @PreAuthorize, @PostAuthorize,
 *  @PreFilter, and @PostFilter by default.
 * */
