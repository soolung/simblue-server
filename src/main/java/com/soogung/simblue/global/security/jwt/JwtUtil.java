package com.soogung.simblue.global.security.jwt;

import com.soogung.simblue.domain.auth.domain.repository.AuthIdRepository;
import com.soogung.simblue.global.security.jwt.exception.ExpiredTokenException;
import com.soogung.simblue.global.security.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.soogung.simblue.global.config.properties.JwtConstants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProperties jwtProperties;
    private final AuthIdRepository authIdRepository;

    public String resolveToken(HttpServletRequest request){
        String bearer = request.getHeader(jwtProperties.getHeader());

        return parseToken(bearer);
    }

    public String parseToken(String bearer){
        if(!Objects.equals(bearer, "") && bearer != null){
            String token = bearer.replaceAll(jwtProperties.getPrefix(), "").trim();
            checkingIfJwtExpired(token);
            return token;
        }
        return null;
    }

    public void checkingIfJwtExpired(String token){
        String authId = getJwt(token).getBody().get(AUTH_ID.getMessage()).toString();

        authIdRepository.findByAuthId(authId)
                .orElseThrow(() -> ExpiredTokenException.EXCEPTION);
    }

    public Jws<Claims> getJwt(String token){
        if(token == null){
            throw InvalidTokenException.EXCEPTION;
        }
        return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
    }



    public Claims getJwtBody(String bearer){
        Jws<Claims> jwt = getJwt(parseToken(bearer));
        return jwt.getBody();
    }
}
