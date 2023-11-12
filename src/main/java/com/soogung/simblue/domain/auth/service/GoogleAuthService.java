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
import com.soogung.simblue.global.feign.auth.dto.response.GoogleInformationResponse;
import com.soogung.simblue.global.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final AuthProperties authProperties;
    private final GoogleAuthClient googleAuthClient;
    private final GoogleInformationClient googleInformationClient;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Transactional
    public TokenResponse execute(String code, AuthType type) {
        boolean isLogin = false;
        String accessToken = googleAuthClient.getAccessToken(
                createGoogleAuthRequest(code, type)).getAccessToken();
        GoogleInformationResponse response = googleInformationClient.getUserInformation(accessToken);
        String email = response.getEmail();
        Authority authority = validateEmailAndGetAuthority(email);

        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isEmpty()) {
            userRepository.save(
                    User.builder()
                            .name(response.getName())
                            .email(email)
                            .authority(authority)
                            .build()
            );
        } else if (
                user.get().isTeacher() ||
                user.get().isStudent() && Objects.nonNull(user.get().getStudentNumber())
        ) {
            isLogin = true;
        }

        return TokenResponse.builder()
                .accessToken(tokenService.createAccessToken(email))
                .refreshToken(tokenService.createRefreshToken(email))
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
