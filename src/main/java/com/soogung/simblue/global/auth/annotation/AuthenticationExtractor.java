package com.soogung.simblue.global.auth.annotation;

import com.soogung.simblue.global.config.properties.JwtProperties;
import com.soogung.simblue.global.auth.exception.EmptyTokenException;
import com.soogung.simblue.global.auth.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@RequiredArgsConstructor
@Component
public class AuthenticationExtractor {

    private final JwtProperties jwtProperties;

    public String extract(NativeWebRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        validate(authorizationHeader);

        return parseToken(authorizationHeader);
    }

    private void validate(String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw EmptyTokenException.EXCEPTION;
        }

        if (!authorizationHeader.startsWith(jwtProperties.getPrefix())) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private String parseToken(String authorizationHeader) {
        return authorizationHeader.replace(jwtProperties.getPrefix(), "").trim();
    }
}