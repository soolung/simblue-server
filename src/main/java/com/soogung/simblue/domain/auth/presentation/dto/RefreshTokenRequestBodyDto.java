package com.soogung.simblue.domain.auth.presentation.dto;

import lombok.Getter;

@Getter
public class RefreshTokenRequestBodyDto {
    private String refresh_token;
}
