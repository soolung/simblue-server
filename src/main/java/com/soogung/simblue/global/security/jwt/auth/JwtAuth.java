package com.soogung.simblue.global.security.jwt.auth;

import com.soogung.simblue.global.config.properties.JwtConstants;
import com.soogung.simblue.global.security.auth.AuthDetailsService;
import com.soogung.simblue.global.security.jwt.JwtUtil;
import com.soogung.simblue.global.security.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuth {

    private final JwtUtil jwtUtil;
    private final AuthDetailsService authDetailsService;

    public Authentication authentication(String token){
        Claims claims = jwtUtil.getJwt(token).getBody();

        if(isNotAccessToken(token)){
            throw InvalidTokenException.EXCEPTION;
        }

        UserDetails userDetails = authDetailsService.loadUserByUsername(claims.get(JwtConstants.AUTH_ID.message).toString());
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    private boolean isNotAccessToken(String token) {
        if(token.isEmpty()){
            throw InvalidTokenException.EXCEPTION;
        }
        String role = jwtUtil.getJwt(token).getHeader().get(JwtConstants.TYPE.message).toString();
        return !role.equals(JwtConstants.ACCESS_KEY.message);
    }
}
