package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {
	
	@Autowired
	private JwtRequestFilter authFilter;
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//	{
//		return http
//	            .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF using lambda
//	            .authorizeHttpRequests(auth -> auth
//	                .requestMatchers("/authenticate", "/sign-up", "/order/**").permitAll() // ✅ All open endpoints
//	                .requestMatchers("/api/**").authenticated() // ✅ Protected routes
//	                .anyRequest().denyAll() // Optional: deny everything else
//	            )
//	            .sessionManagement(session -> 
//	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ✅ No session
//	            )
//	            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) // ✅ JWT Filter
//	            .build();
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
		        .csrf(csrf -> csrf.disable())
		        .authorizeHttpRequests(auth -> auth
		            .requestMatchers("/authenticate", "/sign-up", "/order/**").permitAll()
		            .requestMatchers("/api/admin/**").hasRole("ADMIN")
		            .requestMatchers("/api/customer/**").permitAll()// ✅ Admin routes only for admin
		            .requestMatchers("/api/**").authenticated()        // ✅ Other authenticated users
		            .anyRequest().denyAll()
		        )
		        .sessionManagement(session -> 
		            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        )
		        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
		        .build();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	

}
