package com.soogung.simblue.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("jwt")
public class JwtProperties {

    private Long accessTokenValidTime;
    private String prefix;
    private String header;
    private String secretKey;
}
