package com.soogung.simblue.global.auth.service;

import com.soogung.simblue.domain.auth.domain.RefreshToken;
import com.soogung.simblue.domain.auth.domain.repository.RefreshTokenRepository;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.global.auth.exception.ExpiredTokenException;
import com.soogung.simblue.global.auth.exception.InvalidTokenException;
import com.soogung.simblue.global.config.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenService {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserFacade userFacade;

    private Key getSigningKey() {
        byte[] keyBytes = jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String email) {
        return createToken(email, jwtProperties.getAccessTime());
    }

    public String createRefreshToken(String email) {
        String token = createToken(email, jwtProperties.getRefreshTime());

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .token(token)
                        .email(email)
                        .build()
        );

        return token;
    }

    public String createToken(String email, Long time) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(getSigningKey())
                .compact();
    }

    public User getUser(String token) {
        return userFacade.getUserByEmail(getEmail(token));
    }

    private String getEmail(String token) {
        return extractAllClaims(token)
                .get("email", String.class);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
}
