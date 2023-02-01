package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.global.config.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleAuthLinkService {

    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s" +
            "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email";

    private final AuthProperties authProperties;

    public String execute() {
        return authProperties.getGoogleBaseUrl() +
                String.format(QUERY_STRING, authProperties.getGoogleClientId(), authProperties.getGoogleRedirectUrl());
    }
}
