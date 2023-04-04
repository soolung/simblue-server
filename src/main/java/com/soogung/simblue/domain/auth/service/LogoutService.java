package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.repository.AuthIdRepository;
import com.soogung.simblue.domain.auth.domain.repository.RefreshTokenRepository;
import com.soogung.simblue.global.config.properties.JwtConstants;
import com.soogung.simblue.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogoutService {

    private final JwtUtil jwtUtil;
    private final AuthIdRepository authIdRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Transactional
    public String execute(String bearerRefreshToken){
        String authId = jwtUtil.getJwtBody(bearerRefreshToken).get(JwtConstants.AUTH_ID.message).toString();


        authIdRepository.findByAuthId(authId)
                .ifPresent(authIdRepository::delete);


        refreshTokenRepository.findById(authId)
                .ifPresent(refreshTokenRepository::delete);

        SecurityContextHolder.clearContext();

        return authId;
    }
}
