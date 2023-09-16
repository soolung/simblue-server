package com.soogung.simblue.domain.user.service;

import com.soogung.simblue.domain.user.domain.User;
import com.soogung.simblue.domain.user.presentation.dto.request.StudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinStudentService {

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(User user, StudentRequest request) {
        user.updatePassword(passwordEncoder.encode(request.getPassword()));
        user.updateStudentInformation(request.getStudentNumber(), request.getAdmissionYear());
    }
}
