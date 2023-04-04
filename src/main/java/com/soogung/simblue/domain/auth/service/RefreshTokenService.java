package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.RefreshToken;
import com.soogung.simblue.domain.auth.domain.repository.RefreshTokenRepository;
import com.soogung.simblue.global.feign.auth.dto.response.BsmTokenResponse;
import com.soogung.simblue.global.security.jwt.JwtTokenProvider;
import com.soogung.simblue.global.security.jwt.exception.RefreshTokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtProvider;

    public BsmTokenResponse execute(final String bearerRefreshToken) {
        if(bearerRefreshToken == null) throw RefreshTokenExpiredException.EXCEPTION;
        RefreshToken redisRefreshToken = refreshTokenRepository.findByRefreshToken(bearerRefreshToken)
                .orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);
        return getNewAccessTokens(redisRefreshToken);
    }

    private BsmTokenResponse getNewAccessTokens(final RefreshToken redisRefreshToken) {
        String newAccessToken = jwtProvider.createAccessToken(redisRefreshToken.getId(), redisRefreshToken.getRole());

        return BsmTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(redisRefreshToken.getRefreshToken())
                .expiredAt(redisRefreshToken.getExpiredAt())
                .build();
    }
}
