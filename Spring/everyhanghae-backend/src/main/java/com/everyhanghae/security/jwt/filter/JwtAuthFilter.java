package com.everyhanghae.security.jwt.filter;

import com.everyhanghae.security.jwt.service.JwtService;
import com.everyhanghae.security.jwt.exception.custom.NotValidJwtException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String ATTRIBUTE_JWT_EXCEPTION = "exception";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isNoAuthUrl(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims info = jwtService.getTokenClaim(request);
            setAuthentication(info.getSubject());
        } catch (NotValidJwtException e) {
            request.setAttribute(ATTRIBUTE_JWT_EXCEPTION, e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private boolean isNoAuthUrl(HttpServletRequest request) {
        String requestURL = request.getRequestURI();
        String requestMethod = request.getMethod();

        if (requestURL.contains("/api/users")) {
            return true;
        }

        if (requestMethod.equals("GET") && requestURL.contains("/api/boards")) {
            return true;
        }

        return false;
    }

    private void setAuthentication(String email) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtService.createAuthentication(email);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }


}
