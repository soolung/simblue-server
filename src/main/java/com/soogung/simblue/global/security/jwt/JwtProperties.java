package com.soogung.simblue.global.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String header;
    private final String secret;
    private final Long accessExp;
    private final Long refreshExp;
    private final String prefix;

    public JwtProperties(String header, String secret, Long accessExp, Long refreshExp, String prefix) {
        this.header = header;
        this.secret = secret;
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
        this.prefix = prefix;
    }

}

