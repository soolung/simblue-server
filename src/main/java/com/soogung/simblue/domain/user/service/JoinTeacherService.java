package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.presentation.dto.request.TeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinTeacherService {

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(User user, TeacherRequest request) {
        user.updatePassword(passwordEncoder.encode(request.getPassword()));
    }
}
