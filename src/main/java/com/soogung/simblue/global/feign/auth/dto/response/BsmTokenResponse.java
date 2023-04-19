package com.soogung.simblue.global.feign.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class BsmTokenResponse {
    private final String accessToken;
    private final String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final ZonedDateTime expiredAt;

    @Builder
    public BsmTokenResponse(String accessToken, String refreshToken, ZonedDateTime expiredAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiredAt = expiredAt;
    }
}
