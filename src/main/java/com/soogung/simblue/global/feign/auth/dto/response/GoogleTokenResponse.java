package com.soogung.simblue.global.feign.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
}