package com.soogung.simblue.domain.auth.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.domain.repository.UserRepository;
import com.soogung.simblue.domain.user.domain.type.Authority;
import com.soogung.simblue.domain.user.exception.UserNotFoundException;
import com.soogung.simblue.global.annotation.ServiceWithTransactionalReadOnly;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.response.*;
import leehj050211.bsmOauth.exceptions.BsmAuthCodeNotFoundException;
import leehj050211.bsmOauth.exceptions.BsmAuthInvalidClientException;
import leehj050211.bsmOauth.exceptions.BsmAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class BsmAuthService {

    private final BsmOauth bsmOauth;
    private final UserRepository userRepository;

    @Transactional
    public User execute(String authId) throws IOException {
        String token;
        BsmResourceResponse resource;
        try {
            token = bsmOauth.getToken(authId);
            resource = bsmOauth.getResource(token);
        } catch (BsmAuthCodeNotFoundException | BsmAuthTokenNotFoundException | BsmAuthInvalidClientException e) {
            throw UserNotFoundException.EXCEPTION;
        }

        return updateOrSignUp(resource);
    }

    @Transactional
    public User updateOrSignUp(BsmResourceResponse resource) {
        Optional<User> user = userRepository.findByEmail(resource.getEmail());
        if (user.isEmpty()) {
            return saveUser(resource);
        }
        User updateUser = user.get();

        return updateUser.updateInformationByBsm(resource);
    }

    @Transactional
    public User saveUser(final BsmResourceResponse resource) {
        return userRepository.save(
            User.builder()
                    .email(resource.getEmail())
                    .authority(Authority.ROLE_STUDENT)
                    .name(resource.getStudent().getName())
                    .build()
        );
    }
}

