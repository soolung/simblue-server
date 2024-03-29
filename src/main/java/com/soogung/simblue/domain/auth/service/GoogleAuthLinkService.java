package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.type.AuthType;
import com.soogung.simblue.global.config.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleAuthLinkService {

    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s" +
            "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    private final AuthProperties authProperties;

    public String execute(AuthType authType) {
        return authProperties.getGoogleBaseUrl() +
                String.format(
                        QUERY_STRING,
                        authProperties.getGoogleClientId(),
                        authType.equals(AuthType.SIMBLUE) ? authProperties.getGoogleSimblueRedirectUrl() : authProperties.getGoogleSsamblueRedirectUrl()
                );
    }
}
