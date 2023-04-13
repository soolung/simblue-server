package com.soogung.simblue.domain.user.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Authority authority;
    private String name;
    private String email;
}
