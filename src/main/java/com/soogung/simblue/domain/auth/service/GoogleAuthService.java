package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.type.AuthType;
import com.soogung.simblue.domain.auth.presentation.dto.response.TokenResponse;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.repository.UserRepository;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.exception.NotSchoolMemberException;
import com.soogung.simblue.global.config.properties.AuthProperties;
import com.soogung.simblue.global.feign.auth.GoogleAuthClient;
import com.soogung.simblue.global.feign.auth.GoogleInformationClient;
import com.soogung.simblue.global.feign.auth.dto.request.GoogleAuthRequest;
import com.soogung.simblue.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final AuthProperties authProperties;
    private final GoogleAuthClient googleAuthClient;
    private final GoogleInformationClient googleInformationClient;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(String code, AuthType type) {
        boolean isLogin = true;
        String accessToken = googleAuthClient.getAccessToken(
                createGoogleAuthRequest(code, type)).getAccessToken();
        String email = googleInformationClient.getUserInformation(accessToken).getEmail();
        Authority authority = validateEmailAndGetAuthority(email);

        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isEmpty()) {
            isLogin = false;

            userRepository.save(
                    User.builder()
                            .email(email)
                            .authority(authority)
                            .build()
            );
        } else if (user.get().getName() == null || user.get().getName().equals("")) {
            isLogin = false;
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(email))
                .refreshToken(jwtTokenProvider.createRefreshToken(email))
                .authority(authority)
                .isLogin(isLogin)
                .build();
    }

    private Authority validateEmailAndGetAuthority(String email) {
        String[] splitEmail = email.split("@");
        if (!splitEmail[1].equals("bssm.hs.kr")) {
            throw NotSchoolMemberException.EXCEPTION;
        }

        return splitEmail[0].startsWith("teacher") ? Authority.ROLE_TEACHER : Authority.ROLE_STUDENT;
    }

    private GoogleAuthRequest createGoogleAuthRequest(String code, AuthType type) {
        return GoogleAuthRequest.builder()
                .code(code)
                .clientId(authProperties.getGoogleClientId())
                .clientSecret(authProperties.getGoogleClientSecret())
                .redirectUri(type.equals(AuthType.SIMBLUE) ? authProperties.getGoogleSimblueRedirectUrl() : authProperties.getGoogleSsamblueRedirectUrl())
                .build();
    }
}
