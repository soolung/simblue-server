package com.soogung.simblue.domain.user.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {
    ROLE_STUDENT("STUDENT"),
    ROLE_TEACHER("TEACHER");

    private final String role;
}
