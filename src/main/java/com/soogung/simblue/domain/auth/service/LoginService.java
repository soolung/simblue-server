package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.AuthId;
import com.soogung.simblue.domain.auth.domain.repository.AuthIdRepository;
import com.soogung.simblue.domain.auth.presentation.dto.request.LoginRequest;
import com.soogung.simblue.domain.auth.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.exception.PasswordMismatchException;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.global.security.jwt.JwtProperties;
import com.soogung.simblue.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final BsmAuthService bsmAuthService;
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthIdRepository authIdRepository;
    private final JwtProperties jwtProperties;

    public TokenResponse execute(LoginRequest request) {
        User user = userFacade.findUserByEmail(request.getEmail());
        checkPassword(request.getPassword(), user.getPassword());

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getEmail()))
                .refreshToken(jwtTokenProvider.createRefreshToken(user.getEmail()))
                .isLogin(!(user.getName() == null || user.getName().equals("")))
                .build();
    }

    private void checkPassword(String actual, String expected) {
        if (!passwordEncoder.matches(actual, expected)) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }

    public TokenResponse execute(String authId) throws IOException {

        User user = bsmAuthService.execute(authId);
        saveAuthId(user.getEmail());

        return jwtTokenProvider.createAccessToken(user.getEmail(), user.getAuthority().name());
    }

    private void saveAuthId(String email) {
        authIdRepository.save(
                AuthId.builder()
                        .id(email)
                        .authId(email)
                        .ttl(jwtProperties.getRefreshExp())
                        .build()
        );
    }
}
