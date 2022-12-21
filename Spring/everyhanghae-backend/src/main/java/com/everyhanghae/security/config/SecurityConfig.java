package com.everyhanghae.security.config;

import com.everyhanghae.security.auth.PrincipalOauth2UserService;
import com.everyhanghae.security.exception.CustomAuthenticationEntryPoint;
import com.everyhanghae.security.jwt.filter.JwtAuthFilter;
import com.everyhanghae.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
	@Autowired private PrincipalOauth2UserService principalOauth2UserService;

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
				.authenticated()
				.and()
				.oauth2Login()				//추가
					.loginPage("/login")			// 인증이 필요한 URL에 접근하면 /login으로 이동
					.defaultSuccessUrl("/")			// 로그인 성공하면 "/" 으로 이동
					.failureUrl("/login")			// 로그인 실패 시 /login으로 이동
					.userInfoEndpoint()			// 로그인 성공 후 사용자정보를 가져온다
					.userService(principalOauth2UserService);		//사용자정보를 처리할 때 사용한다

		http.addFilterBefore(new JwtAuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint());

		return http.build();
	}

}
