package com.soogung.simblue.global.security.jwt.filter;

import com.soogung.simblue.global.security.jwt.JwtUtil;
import com.soogung.simblue.global.security.jwt.auth.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuth jwtAuth;
    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);
        SetAuthenticationInSecurityContext(token);
        filterChain.doFilter(request, response);
    }
    private void SetAuthenticationInSecurityContext(String token){
        if(token != null) {
            Authentication authentication = jwtAuth.authentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
