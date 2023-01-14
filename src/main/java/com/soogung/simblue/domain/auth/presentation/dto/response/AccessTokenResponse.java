package com.soogung.simblue.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenResponse {

    private String accessToken;
}
