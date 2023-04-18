package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryCurrentUserService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public UserResponse execute() {
        User user = userFacade.getCurrentUser();

        return UserResponse.builder()
                .authority(user.getAuthority())
                .name(user.getName())
                .email(user.getEmail())
                .roleId(getRoleId(user))
                .build();
    }

    private Long getRoleId(User user) {
        if (user.getName() == null || user.getName().equals("")) {
            return null;
        }

        if (user.getAuthority() == Authority.ROLE_STUDENT) {
            return userFacade.findStudentByUser(user).getId();
        } else if (user.getAuthority() == Authority.ROLE_TEACHER) {
            return userFacade.findTeacherByUser(user).getId();
        }

        return null;
    }
}
