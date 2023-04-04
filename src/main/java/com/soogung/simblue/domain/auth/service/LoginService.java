package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.AuthId;
import com.soogung.simblue.domain.auth.domain.repository.AuthIdRepository;
import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.global.feign.auth.dto.response.BsmTokenResponse;
import com.soogung.simblue.global.security.jwt.JwtProperties;
import com.soogung.simblue.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final BsmAuthService bsmAuthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthIdRepository authIdRepository;
    private final JwtProperties jwtProperties;

    public BsmTokenResponse execute(String authId) throws IOException {

        User user = bsmAuthService.execute(authId);
        saveAuthId(user.getEmail());

        return jwtTokenProvider.createToken(user.getEmail(), user.getAuthority().name());
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
