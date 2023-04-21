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
    private String studentNumber;
    private Authority authority;

    public static SearchUserResponse of(User user) {
        return SearchUserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .studentNumber(user.getStudentNumber())
                .authority(user.getAuthority())
                .build();
    }
}
