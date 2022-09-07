package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.exception.PasswordMismatchException;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.user.presentation.dto.request.LoginRequest;
import com.soogung.simblue.domain.user.presentation.dto.response.TokenResponse;
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

    public TokenResponse execute(LoginRequest request) {
        User user = userFacade.findUserByEmail(request.getEmail());
        checkPassword(request.getPassword(), user.getPassword());

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getEmail()))
                .build();
    }

    private void checkPassword(String actual, String expected) {
        if (!passwordEncoder.matches(actual, expected)) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }
}
