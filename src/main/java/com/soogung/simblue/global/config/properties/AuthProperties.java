package com.soogung.simblue.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("auth")
public class AuthProperties {

    private OAuth google;

    @Getter
    @Setter
    public static class OAuth {
        private String baseUrl;
        private String clientId;
        private String clientSecret;
        private String simblueRedirectUrl;
        private String ssamblueRedirectUrl;
    }

    public String getGoogleBaseUrl() {
        return google.getBaseUrl();
    }

    public String getGoogleClientId() {
        return google.getClientId();
    }

    public String getGoogleSimblueRedirectUrl() {
        return google.getSimblueRedirectUrl();
    }

    public String getGoogleSsamblueRedirectUrl() {
        return google.getSsamblueRedirectUrl();
    }

    public String getGoogleClientSecret() {
        return google.getClientSecret();
    }
}
