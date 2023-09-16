package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.presentation.dto.request.LoginRequest;
import com.soogung.simblue.domain.auth.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.exception.PasswordMismatchException;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.global.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserFacade userFacade;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse execute(LoginRequest request) {
        User user = userFacade.getUserByEmail(request.getEmail());
        checkPassword(request.getPassword(), user.getPassword());

        return TokenResponse.builder()
                .accessToken(tokenService.createAccessToken(user.getEmail()))
                .refreshToken(tokenService.createRefreshToken(user.getEmail()))
                .authority(user.getAuthority())
                .isLogin(!(user.getName() == null || user.getName().equals("")))
                .build();
    }

    private void checkPassword(String actual, String expected) {
        if (!passwordEncoder.matches(actual, expected)) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }
}
