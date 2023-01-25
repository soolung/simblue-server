package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.RefreshToken;
import com.soogung.simblue.domain.auth.domain.repository.RefreshTokenRepository;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.exception.PasswordMismatchException;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.auth.presentation.dto.request.LoginRequest;
import com.soogung.simblue.domain.auth.presentation.dto.response.TokenResponse;
import com.soogung.simblue.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse execute(LoginRequest request) {
        User user = userFacade.findUserByEmail(request.getEmail());
        checkPassword(request.getPassword(), user.getPassword());

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getEmail()))
                .refreshToken(jwtTokenProvider.createRefreshToken(user.getName()))
                .authority(user.getAuthority())
                .name(user.getName())
                .email(user.getEmail())
                .isLogin(user.getName() != null || !user.getName().equals(""))
                .build();
    }

    private void checkPassword(String actual, String expected) {
        if (!passwordEncoder.matches(actual, expected)) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }
}
