package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.exception.PasswordMismatchException;
import com.soogung.simblue.domain.user.presentation.dto.request.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePasswordService {

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(User user, UpdatePasswordRequest request) {
        validateOldPassword(request.getOldPassword(), user);
        user.updatePassword(passwordEncoder.encode(request.getNewPassword()));
    }

    private void validateOldPassword(String oldPassword, User user) {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }
}
