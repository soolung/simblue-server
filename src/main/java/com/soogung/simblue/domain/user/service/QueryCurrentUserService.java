package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryCurrentUserService {

    @Transactional(readOnly = true)
    public UserResponse execute(User user) {
        return UserResponse.builder()
                .authority(user.getAuthority())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
