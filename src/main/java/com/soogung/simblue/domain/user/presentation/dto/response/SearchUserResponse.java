package com.soogung.simblue.domain.user.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchUserResponse {

    private Long userId;
    private String name;
    private Authority authority;

    public static SearchUserResponse of(User user) {
        return SearchUserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .authority(user.getAuthority())
                .build();
    }
}
