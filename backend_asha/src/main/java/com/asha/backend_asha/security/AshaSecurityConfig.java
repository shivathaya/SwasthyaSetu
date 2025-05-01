package com.asha.backend_asha.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class AshaSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests(auth -> auth.requestMatchers("/ashaUserAuth/**").permitAll() // Public
																													// endpoints
				.requestMatchers("/api/**").authenticated() // Protect API endpoints
		).httpBasic() // Basic authentication for simplicity
				.and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Enable
																												// session
																												// support
				);
		http.cors(cors -> cors.configurationSource(request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(List.of("http://localhost:3000/")); // Your front-end URL
			config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			config.setAllowCredentials(true);
			config.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
			return config;
		}));

		return http.build();
	}
}
