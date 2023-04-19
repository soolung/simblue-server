package com.soogung.simblue.global.security.auth;

import leehj050211.bsmOauth.BsmOauth;
import org.springframework.context.annotation.Bean;

public class BsmOauthProperties {

    private final String client_id;
    private final String secret_key;

    public BsmOauthProperties(String client_id, String secret_key) {
        this.client_id = client_id;
        this.secret_key = secret_key;
    }

    @Bean("auth.bsm")
    public BsmOauth bsmOauth(){
        return new BsmOauth(client_id, secret_key);
    }
}
