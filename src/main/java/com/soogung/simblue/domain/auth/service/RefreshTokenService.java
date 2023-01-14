package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.RefreshToken;
import com.soogung.simblue.domain.auth.domain.repository.RefreshTokenRepository;
import com.soogung.simblue.domain.auth.presentation.dto.response.AccessTokenResponse;
import com.soogung.simblue.global.security.jwt.JwtTokenProvider;
import com.soogung.simblue.global.security.jwt.exception.ExpiredTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AccessTokenResponse execute(String token) {
        RefreshToken refreshToken = getRefreshToken(token);
        return AccessTokenResponse.builder()
                .accessToken(jwtTokenProvider
                        .createAccessToken(refreshToken.getEmail()))
                .build();
    }

    private RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findById(token)
                .orElseThrow(() -> ExpiredTokenException.EXCEPTION);
    }
}
