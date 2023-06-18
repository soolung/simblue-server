package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerResponse {

    private Long userId;
    private String name;

    public static OwnerResponse of(User user) {
        return OwnerResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .build();
    }
}
