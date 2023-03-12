package com.soogung.simblue.domain.auth.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

    private String accessToken;
    private String refreshToken;
    private Authority authority;
    private String name;
    private String email;
    private boolean isLogin;
    private Long roleId;
}

