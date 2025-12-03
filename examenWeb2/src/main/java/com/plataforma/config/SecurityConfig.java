/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.plataforma.config;
import com.plataforma.domain.Usuario;
import com.plataforma.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public SecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario u = usuarioRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No existe: " + username));
            String rol = u.getRol().getNombre(); // ADMIN / PROFESOR / ESTUDIANTE
            var auths = List.of(new SimpleGrantedAuthority("ROLE_" + rol));
            return new User(u.getEmail(), u.getPassword(), u.getActivo(), true, true, true, auths);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // usamos '12345' en texto plano
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService uds, PasswordEncoder enc) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider(uds);
        p.setPasswordEncoder(enc);
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider provider) throws Exception {
        http
            .authenticationProvider(provider)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
            )
            // login por defecto (no usa tu plantilla para evitar problemas de nombres de inputs)
            .formLogin(f -> f.defaultSuccessUrl("/_diag/whoami", true).permitAll())
            .logout(l -> l.permitAll())
            .csrf(c -> c.disable());
        return http.build();
    }
}

