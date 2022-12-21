package com.everyhanghae.security.jwt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.everyhanghae.security.jwt.JwtPayload;
import com.everyhanghae.security.jwt.util.JwtUtil;
import com.everyhanghae.security.service.UserDetailsServiceImpl;
import com.everyhanghae.security.jwt.exception.custom.NotValidJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtService {

	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImpl userDetailsService;

	public Claims getTokenClaim(HttpServletRequest servlet) {
		String token = jwtUtil.resolveToken(servlet);
		if (token == null) {
			throw new NotValidJwtException("로그인을 해주세요.");
		}

		if (!jwtUtil.validateToken(token)) {
			throw new NotValidJwtException("잘못된 토큰 요청입니다.");
		}

		Claims claim = jwtUtil.getUserInfoFromToken(token);
		return claim;
	}

	public void addTokenToHeader(HttpServletResponse servlet, JwtPayload payload) {
		servlet.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(payload));
	}

	public Authentication createAuthentication(String email) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		return new UsernamePasswordAuthenticationToken(userDetails, null, null);
	}

}
