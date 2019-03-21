package com.d2gdemo.googlemap.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;
    public JwtTokenFilter(JwtTokenProvider provider){
        this.jwtTokenProvider = provider;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
String token = jwtTokenProvider.resolveToken(httpServletRequest);
try {
    if (token != null && jwtTokenProvider.validateToken(token)){
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
} catch (IllegalAccessException ex){
    SecurityContextHolder.clearContext();
    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,ex.getMessage());
    return;
}
filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
