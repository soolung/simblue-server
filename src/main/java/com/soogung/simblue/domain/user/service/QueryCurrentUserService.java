package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.facade.UserFacade;
import com.soogung.simblue.domain.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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
                .build();
    }
}
