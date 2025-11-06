package com.axel.pruebatecnica.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// permite solo las rutas especificadas, lo demas requiere autenticacion
		return http.authorizeHttpRequests((authorize) -> authorize
								.requestMatchers("/", 
										"/login", 
										"/api/login", 
										"/api/register",
                                        "/css/**", 
                                        "/js/**", 
                                        "/images/**")
								.permitAll().anyRequest().authenticated())
				// login
				.formLogin(form -> form.loginPage("/api/login")// pagina personalizada login
						.defaultSuccessUrl("/api/home", true) // tras iniciar sesion
						.permitAll())
				// logout
				.logout(logout -> logout.logoutUrl("/logout")// url default logout
						.logoutSuccessUrl("/login?logout") // taer cerrar sesion
						.permitAll())
				// csrf deshabilitado para evitar vulnerabildades
				.csrf(csrf -> csrf.disable())
				//maenjo se sesiones, mantiene la sesion activa aunque cerremos el cliente (always), inicio de sesion cada vz que se cierra el cliente (if_required)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.build();

	}
}
