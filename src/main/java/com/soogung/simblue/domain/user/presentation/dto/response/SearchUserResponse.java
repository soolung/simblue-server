package com.soogung.simblue.domain.user.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.facade.UserFacade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchUserResponse {

    private Long userId;
    private String name;
    private String studentNumber;
    private Authority authority;

    public static SearchUserResponse of(User user, UserFacade userFacade) {
        return SearchUserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .studentNumber(user.getAuthority() == Authority.ROLE_STUDENT ?
                        userFacade.findStudentByUser(user).getStudentNumber() : null)
                .authority(user.getAuthority())
                .build();
    }
}
