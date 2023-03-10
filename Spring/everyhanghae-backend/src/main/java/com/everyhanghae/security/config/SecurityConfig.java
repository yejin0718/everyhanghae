package com.everyhanghae.security.config;

import com.everyhanghae.security.exception.CustomAuthenticationEntryPoint;
import com.everyhanghae.security.jwt.filter.JwtAuthFilter;
import com.everyhanghae.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private final JwtService jwtService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors();

		http.csrf()
			.disable();

		http.httpBasic()
			.disable();

		http.sessionManagement()
			.sessionCreationPolicy(STATELESS);

		http.authorizeRequests()
			.antMatchers("/api/users/**")
				.permitAll()
			.antMatchers(GET, "/api/boards/**")
				.permitAll()
			.anyRequest()
				.authenticated();

		http.addFilterBefore(new JwtAuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint());

		return http.build();
	}

}
