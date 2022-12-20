package com.everyhanghae.security.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtService {

	private final JwtUtil jwtUtil;

	public Claims getTokenClaim(HttpServletRequest servlet) {
		String token = jwtUtil.resolveToken(servlet);
		if (token == null || !jwtUtil.validateToken(token)) {

		}
		Claims claim = jwtUtil.getUserInfoFromToken(token);
		return claim;
	}

	public void addTokenToHeader(HttpServletResponse servlet, JwtPayload payload) {
		servlet.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(payload));
	}

}
