package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.auth.domain.type.AuthType;
import com.soogung.simblue.global.config.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleAuthLinkService {

    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s" +
            "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email";

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
