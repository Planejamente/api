package org.planejamente.planejamente.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api-docs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api-docs/swagger-config/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/psicologos/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pacientes/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/psicologos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/pacientes/csv").permitAll()
                        .requestMatchers(HttpMethod.POST, "/consultas").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/enderecos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/enderecos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "experiencias-formacoes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/experiencias-formacoes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/especialidades").permitAll()
                        .requestMatchers(HttpMethod.POST, "/especialidades").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/especialidades/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}