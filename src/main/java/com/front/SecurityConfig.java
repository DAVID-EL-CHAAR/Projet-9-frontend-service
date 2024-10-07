package com.front;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuration de l'authentification et des requêtes autorisées
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**").permitAll()  // Autorise les ressources statiques
                .anyRequest().authenticated()  // Toutes les autres requêtes nécessitent une authentification
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/ui/patients", true)  // Redirection après un login réussi
                .permitAll()  // Permettre l'accès à la page de login sans authentification
            )
            .logout(logout -> logout
                .permitAll()  // Permettre l'accès à la fonctionnalité de déconnexion sans authentification
            )
            .sessionManagement(session -> session
                .invalidSessionUrl("/login?session=invalid")  // Redirection en cas de session invalide
                .maximumSessions(1)  // Limite à une seule session par utilisateur
                .expiredUrl("/login?session=expired")  // Redirection en cas d'expiration de la session
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))  // Utilisation de BCrypt pour hacher le mot de passe
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Utilisation de BCrypt pour le hachage des mots de passe
    }
}
